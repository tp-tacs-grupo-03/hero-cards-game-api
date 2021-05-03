package utn.tacs.apps.backend.controller.matches;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import utn.tacs.matches.MatchModelResponse;
import utn.tacs.matches.application.find.MatchFindRequest;
import utn.tacs.matches.application.find.MatchesFinder;
import utn.tacs.matches.application.list.MatchLister;
import utn.tacs.matches.application.list.MatchPagingRequest;
import utn.tacs.model.responseModel.CardDataModel;

import java.util.List;



@RequestMapping("api/matches")
@Api(tags = "Matches")
@RestController
public class MatchesGetController {

    MatchesFinder finder;
    MatchLister lister;


    public MatchesGetController(MatchesFinder finder) {
        this.finder = finder;
    }

    @GetMapping
    @ApiOperation(value = "Obtener todas las partidas")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "Las partidas")
    })
    public List<MatchModelResponse> getAllMatches(@RequestParam(value = "page",required = false, defaultValue = "0") int page,
                                          @RequestParam(value = "pageSize",required = false, defaultValue = "10") int pageSize,
                                          @RequestParam(value = "sortBy",required = false) String sortField,
                                          @RequestParam(value = "sortDirection",required = false, defaultValue = "asc") String sortDirection){
        return lister.list(new MatchPagingRequest(sortField, page, pageSize,sortDirection));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener una partida por id")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "La partida")
    })
    public MatchModelResponse getMatch(@PathVariable("id") String id) throws Exception {
        return finder.find(new MatchFindRequest(id));
    }


    @GetMapping("/{id}/cards")
    @ApiOperation(value = "Obtener carta actual del mazo")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "La carta")
    })
    public CardDataModel draw(@PathVariable("id") int id){
        return null;
    }

}
