package utn.tacs.services;

import org.springframework.stereotype.Service;
import utn.tacs.domain.Battle;
import utn.tacs.domain.Match;
import utn.tacs.dto.battle.BattleModelResponse;
import utn.tacs.dto.battle.MatchBattleRequest;
import utn.tacs.repositories.MatchesRepository;

@Service
public class MatchBattle {

    private MatchesRepository repository;

    public MatchBattle(MatchesRepository repository) {
        this.repository = repository;
    }

    public BattleModelResponse battle(MatchBattleRequest matchBattleRequest) throws Exception {
        final Match match = repository.find(matchBattleRequest.getMatchId()).orElseThrow(()-> new Exception("No hay match con ese id"));
        final Battle battle = match.battle(matchBattleRequest);
        repository.update(match);
        return new BattleModelResponse(battle.getAttribute(), battle.getPlayers(), battle.getWinner());
    }
}
