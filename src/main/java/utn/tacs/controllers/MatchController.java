package utn.tacs.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import utn.tacs.domain.Stats;
import utn.tacs.dto.battle.BattleModelResponse;
import utn.tacs.dto.battle.ListBattles;
import utn.tacs.dto.battle.MatchBattleRequest;
import utn.tacs.dto.deck.response.MatchModel;
import utn.tacs.dto.deck.response.Request;
import utn.tacs.dto.match.*;
import utn.tacs.pagination.exceptions.PaginationException;
import utn.tacs.services.*;
import utn.tacs.sorting.exceptions.SortingException;

import java.util.ArrayList;
import java.util.List;



@RequestMapping("api/matches")
@Api(tags = "Matches")
@RestController
public class MatchController {

    private MatchService matchService;
    private Stats stats;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    @ApiOperation(value = "Obtener todas las partidas")
    @ApiResponses({
            @ApiResponse(code = 200, response = ListMatchModelResponse.class, message = "Las partidas")
    })
    public ListMatchModelResponse getAllMatches(@RequestParam(value = "offSet",required = false, defaultValue = "0") int offSet,
                                                  @RequestParam(value = "limit",required = false, defaultValue = "100") int limit,
                                                  @RequestParam(value = "sortBy",required = false, defaultValue = "id") String sortField ,
                                                  @RequestParam(value = "sortDirection",required = false, defaultValue = "asc") String sortDirection){
        try {
            return matchService.findAll(new MatchPagingRequest(sortField, offSet, limit,sortDirection));
        } catch (PaginationException | SortingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener una partida por id")
    @ApiResponses({
            @ApiResponse(code = 200, response = MatchModelResponse.class, message = "La partida")
    })
    public MatchModelResponse getMatch(@PathVariable("id") String id) throws Exception {
        return matchService.find(new MatchFindRequest(id));
    }

    @GetMapping("/{id}/battles")
    @ApiOperation(value = "Obtener las batallas de un match")
    @ApiResponses({
            @ApiResponse(code = 200, response = ListBattles.class, message = "Lista de batallas")
    })
    public ListBattles getBattles(@PathVariable("id") String id) throws Exception {
        final ListBattles listBattles = new ListBattles();
        listBattles.setBattles(matchService.findBattles(new MatchFindRequest(id)));
        return listBattles;
    }

    @PostMapping
    @ApiOperation(value = "Crear partida, se divide y mezcla el mazo elegido")
    @ApiResponses({
            @ApiResponse(code = 200, response = MatchModelResponse.class, message = "match")
    })
    public MatchModelResponse postMatch(@Validated @NonNull @RequestBody MatchModel matchRequest) throws Exception {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final String hostId = auth.getName();

        final List<String> players = new ArrayList<>();
        players.add(hostId);
        players.add(matchRequest.getOpponentId());
        MatchCreateRequest matchCreateRequest = new MatchCreateRequest(players, matchRequest.getDeckId(), hostId);
        MatchModelResponse matchModelResponse = matchService.create(matchCreateRequest);
        return matchModelResponse;
    }


    @PostMapping("/{id}/battles")
    @ApiOperation(value = "Indicar en tu turno que atributo se usara")
    @ApiResponses({
            @ApiResponse(code = 200, response = BattleModelResponse.class, message = "Resultado del combate")
    })
    public BattleModelResponse modifyMatch(@RequestBody Request request, @PathVariable("id") String id ) throws Exception {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return matchService.begin(new MatchBattleRequest(id, auth.getName(), request.getAttribute()));
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "Actualizar partida")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Surrender")
    })
    public void surrender(@RequestBody MatchRequest request, @PathVariable("id") String id ) throws Exception {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final MatchUpdateRequest matchUpdateRequest = new MatchUpdateRequest();
        matchUpdateRequest.setStatus(request.getStatus());
        matchUpdateRequest.setId(id);
        matchUpdateRequest.setPlayer(auth.getName());
        matchService.update(matchUpdateRequest);
    }

}
