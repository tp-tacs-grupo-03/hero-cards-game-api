package utn.tacs.matches.application.battle;

import org.springframework.stereotype.Service;
import utn.tacs.matches.domain.Battle;
import utn.tacs.matches.domain.Match;
import utn.tacs.matches.domain.MatchesRepository;

@Service
public class MatchBattle {

    MatchesRepository repository;

    public MatchBattle(MatchesRepository repository) {
        this.repository = repository;
    }

    public BattleModelResponse battle(MatchBattleRequest matchBattleRequest) throws Exception {
        Match match = repository.find(matchBattleRequest.getMatchId()).orElseThrow(()-> new Exception("No hay match con ese id"));
        Battle battle = match.battle(matchBattleRequest);
        repository.update(match);
        return new BattleModelResponse(battle.getAttribute(), battle.getPlayers(), battle.getWinner());
    }
}
