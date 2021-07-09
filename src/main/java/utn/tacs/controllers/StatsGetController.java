package utn.tacs.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import utn.tacs.dto.deck.response.ListPlayerStatsModel;
import utn.tacs.dto.match.MatchStatsModel;
import utn.tacs.dto.player.PlayerStatsModel;
import utn.tacs.services.StatsService;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequestMapping("api/stats")
@Api(tags = "Stats")
@RestController
@CrossOrigin(value = "*", exposedHeaders = {"ETag"})
@AllArgsConstructor
public class StatsGetController {

    private final StatsService statsService;

    @GetMapping("/matches")
    @ApiOperation(value = "Obtener cantidad de partidas de determinado estado entre determinadas fechas")
    @ApiResponses({
            @ApiResponse(code = 200, response = MatchStatsModel.class, message = "Estadisticas de las partidas entre las fechas informadas")
    })
    @PreAuthorize(value = "hasAuthority('read:stats')")
    public MatchStatsModel getMatches(@RequestParam(value = "initDate", required = false) String initDate, @RequestParam(value = "finishDate",required = false) String finishDate ) throws Exception {
        return statsService.findMatches(initDate, finishDate);
    }

    @GetMapping("/leadderboard")
    @ApiOperation(value = "Obtener listado de estadisticas de los mejores jugadores")
    @ApiResponses({
            @ApiResponse(code = 200, response = ListPlayerStatsModel.class, message = "Tablero de posiciones")
    })
    @PreAuthorize(value = "hasAuthority('read:stats')")
    public ListPlayerStatsModel getLeadderboard(@RequestParam(value = "offSet",required = false, defaultValue = "0") int offSet,
                                                @RequestParam(value = "limit",required = false, defaultValue = "100") int limit,
                                                @RequestParam(value = "sortBy",required = false) String sortField,
                                                @RequestParam(value = "sortDirection",required = false, defaultValue = "asc") String sortDirection){
        final ListPlayerStatsModel stats = new ListPlayerStatsModel();
        stats.setPlayerStatsModels(statsService.findAll());
        return stats;
    }

    @GetMapping("/users/{userId}")
    @ApiOperation(value = "Obtener estadisticas personales de un usuario")
    @ApiResponses({
            @ApiResponse(code = 200, response = PlayerStatsModel.class, message = "Estadisticas de un usuario")
    })
    @PreAuthorize(value = "hasAuthority('read:stats')")
    public PlayerStatsModel getRecord(@PathVariable("userId") String userId) {
        return statsService.find(userId.replace("%7C", "|"));
    }
}
