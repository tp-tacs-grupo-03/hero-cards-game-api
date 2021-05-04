package utn.tacs.apps.backend.controller.matches;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utn.tacs.matches.MatchModelResponse;
import utn.tacs.matches.application.battle.BattleModelResponse;
import utn.tacs.matches.application.battle.MatchBattle;
import utn.tacs.matches.application.battle.MatchBattleRequest;
import utn.tacs.matches.application.create.MatchCreateRequest;
import utn.tacs.matches.application.create.MatchesCreator;
import utn.tacs.model.responseModel.MatchModel;
import utn.tacs.model.responseModel.Request;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/matches")
@Api(tags = "Matches")
@CrossOrigin("*")
@RestController
public class MatchesPostController {

    MatchesCreator creator;
    MatchBattle battle;

    public MatchesPostController(MatchesCreator creator, MatchBattle battle) {
        this.creator = creator;
        this.battle = battle;
    }

    @PostMapping
    @ApiOperation(value = "Crear partida, se divide y mezcla el mazo elegido")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "match")
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
            @ApiResponse(code = 200, response = Object.class, message = "Resultado del combate")
    })
    public BattleModelResponse modifyMatch(@RequestBody Request request, @PathVariable("id") String id ) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return battle.battle(new MatchBattleRequest(id, auth.getName(), request.getAttribute()));
    }
}
