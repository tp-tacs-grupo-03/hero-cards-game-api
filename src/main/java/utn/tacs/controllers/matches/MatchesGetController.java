package utn.tacs.controllers.matches;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import utn.tacs.dto.card.CardModelResponse;
import utn.tacs.dto.match.MatchDrawRequest;
import utn.tacs.dto.match.MatchFindRequest;
import utn.tacs.dto.match.MatchModelResponse;
import utn.tacs.dto.match.MatchPagingRequest;
import utn.tacs.services.MatchCardDrawer;
import utn.tacs.services.MatchesFinder;

import java.util.List;



@RequestMapping("api/matches")
@Api(tags = "Matches")
@CrossOrigin("*")
@RestController
public class MatchesGetController {

    MatchesFinder finder;
    MatchCardDrawer cardDraw;

    public MatchesGetController(MatchesFinder finder, MatchCardDrawer cardDraw) {
        this.finder = finder;
        this.cardDraw = cardDraw;
    }

    @GetMapping
    @ApiOperation(value = "Obtener todas las partidas")
    @ApiResponses({
            @ApiResponse(code = 200, response = MatchModelResponse.class, responseContainer = "List", message = "Las partidas")
    })
    public List<MatchModelResponse> getAllMatches(@RequestParam(value = "page",required = false, defaultValue = "0") int page,
                                                  @RequestParam(value = "pageSize",required = false, defaultValue = "10") int pageSize,
                                                  @RequestParam(value = "sortBy",required = false) String sortField,
                                                  @RequestParam(value = "sortDirection",required = false, defaultValue = "asc") String sortDirection){
        return finder.findAll(new MatchPagingRequest(sortField, page, pageSize,sortDirection));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener una partida por id")
    @ApiResponses({
            @ApiResponse(code = 200, response = MatchModelResponse.class, message = "La partida")
    })
    public MatchModelResponse getMatch(@PathVariable("id") String id) throws Exception {
        return finder.find(new MatchFindRequest(id));
    }


    @GetMapping("/{id}/cards")
    @ApiOperation(value = "Obtener carta actual del mazo")
    @ApiResponses({
            @ApiResponse(code = 200, response = CardModelResponse.class, message = "La carta")
    })
    public CardModelResponse draw(@PathVariable("id") String id) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String playerId = auth.getName();
        return cardDraw.draw(new MatchDrawRequest(id, playerId));
    }

}
