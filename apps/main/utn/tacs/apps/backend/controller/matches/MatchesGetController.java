package utn.tacs.apps.backend.controller.matches;

import com.tacs.tacs.model.responseModel.CardDataModel;
import com.tacs.tacs.model.responseModel.MatchModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/matches")
@RestController
public class MatchesGetController {
    @GetMapping
    @ApiOperation(value = "Obtener todas las partidas")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "Las partidas")
    })
    public List<MatchModel> getAllMatches(@RequestParam(value = "page",required = false, defaultValue = "0") int page,
                                          @RequestParam(value = "pageSize",required = false, defaultValue = "10") int pageSize,
                                          @RequestParam(value = "sortBy",required = false) String sortField,
                                          @RequestParam(value = "sortDirection",required = false, defaultValue = "asc") String sortDirection){
        return null;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener una partida por id")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "La partida")
    })
    public MatchModel getMatch(@PathVariable("id") int id){
        return null;
    }


    @GetMapping("/{id}/cards")
    @ApiOperation(value = "Obtener carta actual del mazo")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "La carta")
    })
    public CardDataModel draw(@PathVariable("id") int id){
        return null;
    }

    @GetMapping("/{id}/turns")
    @ApiOperation(value = "Recrear una partida")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "La partida recreada")
    })
    public MatchModel recreateMatch(@PathVariable("id") int id){
        return null;
    }
}
