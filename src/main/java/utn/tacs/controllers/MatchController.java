package utn.tacs.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utn.tacs.domain.Battle;
import utn.tacs.dto.battle.BattleModelResponse;
import utn.tacs.dto.battle.MatchBattleRequest;
import utn.tacs.dto.card.CardModelResponse;
import utn.tacs.dto.deck.response.MatchModel;
import utn.tacs.dto.deck.response.Request;
import utn.tacs.dto.match.*;
import utn.tacs.services.MatchBattleHandler;
import utn.tacs.services.MatchCardDrawer;
import utn.tacs.services.MatchesCreator;
import utn.tacs.services.MatchesFinder;

import java.util.ArrayList;
import java.util.List;



@RequestMapping("api/matches")
@Api(tags = "Matches")
@CrossOrigin("*")
@RestController
public class MatchController {

    private MatchesFinder finder;
    private MatchCardDrawer cardDraw;
    private MatchesCreator creator;
    private MatchBattleHandler battle;

    public MatchController(MatchesFinder finder, MatchCardDrawer cardDraw, MatchesCreator creator, MatchBattleHandler battle) {
        this.finder = finder;
        this.cardDraw = cardDraw;
        this.creator = creator;
        this.battle = battle;
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

    @GetMapping("/{id}/battles")
    @ApiOperation(value = "Obtener las batallas de un match")
    @ApiResponses({
            @ApiResponse(code = 200, response = Battle.class, responseContainer = "List", message = "Lista de batallas")
    })
    public List<Battle> getBattles(@PathVariable("id") String id) throws Exception {
        return finder.find(new MatchFindRequest(id)).getBattles();
    }

    @PostMapping
    @ApiOperation(value = "Crear partida, se divide y mezcla el mazo elegido")
    @ApiResponses({
            @ApiResponse(code = 200, response = MatchModelResponse.class, message = "match")
    })
    public MatchModelResponse postMatch(@Validated @NonNull @RequestBody MatchModel matchRequest) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String hostId = auth.getName();

        List<String> players = new ArrayList<>();
        players.add(hostId);
        players.add(matchRequest.getOpponentId());

        return creator.create(new MatchCreateRequest(players, matchRequest.getDeckId()));
    }


    @PostMapping("/{id}/battles")
    @ApiOperation(value = "Indicar en tu turno que atributo se usara")
    @ApiResponses({
            @ApiResponse(code = 200, response = BattleModelResponse.class, message = "Resultado del combate")
    })
    public BattleModelResponse modifyMatch(@RequestBody Request request, @PathVariable("id") String id ) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return battle.begin(new MatchBattleRequest(id, auth.getName(), request.getAttribute()));
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "Surrendear partida")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Surrender")
    })
    public void surrender(@PathVariable("id") String id ) {
    }

}
