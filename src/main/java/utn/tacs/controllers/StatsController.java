package utn.tacs.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import utn.tacs.dto.deck.response.ListPlayerStatsModel;
import utn.tacs.dto.match.MatchStatsModel;
import utn.tacs.dto.player.PlayerStatsModel;
import utn.tacs.services.StatsService;
import utn.tacs.sorting.Sort;
import utn.tacs.sorting.exceptions.SortingException;

@RequestMapping("api/stats")
@Api(tags = "Stats")
@RestController
@CrossOrigin(value = "*", exposedHeaders = {"ETag"})
@AllArgsConstructor
public class StatsController {

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

    //TODO ordenar por Name, ganadas, perdidas, jugadas (asc, desc)
    @GetMapping("/leadderboard")
    @ApiOperation(value = "Obtener listado de estadisticas de los mejores jugadores")
    @ApiResponses({
            @ApiResponse(code = 200, response = ListPlayerStatsModel.class, message = "Tablero de posiciones")
    })
    @PreAuthorize(value = "hasAuthority('read:stats')")
    public ListPlayerStatsModel getLeadderboard(@RequestParam(value = "page",required = false, defaultValue = "0") int page,
                                                @RequestParam(value = "size",required = false, defaultValue = "100") int size,
                                                @RequestParam(value = "sortBy",required = false, defaultValue = "") String sortField,
                                                @RequestParam(value = "sortDirection",required = false, defaultValue = "ASC") String sortDirection){
        final Pageable pageable = PageRequest.of(page, size);
        try {
            final Sort sort = new Sort(sortField, sortDirection);
            final ListPlayerStatsModel stats = new ListPlayerStatsModel();
            stats.setPlayerStatsModels(statsService.findAll(pageable, sort));
            return stats;
        } catch (SortingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception pe) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, pe.getMessage(), pe);
        }
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
