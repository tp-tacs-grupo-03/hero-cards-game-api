package utn.tacs.controllers.stats;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import utn.tacs.dto.deck.response.PlayerStatsModel;

import java.util.List;

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
            @ApiResponse(code = 200, response = PlayerStatsModel.class, responseContainer = "List", message = "Tablero de posiciones")
    })
    public List<PlayerStatsModel> getLeadderboard(@RequestParam(value = "page",required = false, defaultValue = "0") int page,
                                                  @RequestParam(value = "pageSize",required = false, defaultValue = "10") int pageSize,
                                                  @RequestParam(value = "sortBy",required = false) String sortField,
                                                  @RequestParam(value = "sortDirection",required = false, defaultValue = "asc") String sortDirection){
        return null;
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
