package utn.tacs.controllers;

import com.google.common.collect.Range;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import utn.tacs.dto.deck.response.ListPlayerStatsModel;
import utn.tacs.dto.deck.response.PlayerStatsModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequestMapping("api/stats")
@Api(tags = "Stats")
@RestController
public class StatsGetController {

    @GetMapping("/matches")
    @ApiOperation(value = "Obtener cantidad de partidas de determinado estado entre determinadas fechas")
    @ApiResponses({
            @ApiResponse(code = 200, response = Integer.class, message = "Estadisticas de las partidas en el estado dado entre las fechas informadas")
    })
    public int getMatches(@RequestParam(value = "matchStatus",required = false) String matchStatus, @RequestParam(value = "initDate", required = false) String initDate, @RequestParam(value = "finishDate",required = false) String finishDate ){
        return 0;
    }

    @GetMapping("/leadderboard")
    @ApiOperation(value = "Obtener listado de estadisticas de los mejores jugadores")
    @ApiResponses({
            @ApiResponse(code = 200, response = ListPlayerStatsModel.class, message = "Tablero de posiciones")
    })
    public ListPlayerStatsModel getLeadderboard(@RequestParam(value = "page",required = false, defaultValue = "0") int page,
                                                @RequestParam(value = "pageSize",required = false, defaultValue = "10") int pageSize,
                                                @RequestParam(value = "sortBy",required = false) String sortField,
                                                @RequestParam(value = "sortDirection",required = false, defaultValue = "asc") String sortDirection){
        //TODO implementar la llamada a servicios para obtener los leadderboards
        ListPlayerStatsModel stats = new ListPlayerStatsModel();
        int randomNum = ThreadLocalRandom.current().nextInt(1, 1000 + 1);
        List<Integer> collect = IntStream.range(0, 10000).boxed().collect(Collectors.toList());
        collect.forEach(i -> {
            stats.addPlayersStatsModel(new PlayerStatsModel("" + randomNum, randomNum * 13, randomNum * 23, randomNum * 17));
        });
        return stats;
    }

    @GetMapping("/users/{userId}")
    @ApiOperation(value = "Obtener estadisticas personales de un usuario")
    @ApiResponses({
            @ApiResponse(code = 200, response = PlayerStatsModel.class, message = "Estadisticas de un usuario")
    })
    public PlayerStatsModel getRecord(@PathVariable("userId") String userId){
        return null;
    }
}
