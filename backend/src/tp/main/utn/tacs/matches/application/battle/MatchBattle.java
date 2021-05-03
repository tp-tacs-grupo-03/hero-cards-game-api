package utn.tacs.matches.application.battle;

import org.springframework.stereotype.Service;
import utn.tacs.matches.MatchModelResponse;
import utn.tacs.matches.domain.Match;
import utn.tacs.matches.domain.MatchesRepository;

@Service
public class MatchBattle {

    MatchesRepository repository;

    public MatchBattle(MatchesRepository repository) {
        this.repository = repository;
    }

    public MatchModelResponse battle(MatchBattleRequest matchBattleRequest) throws Exception {
        Match match = repository.find(matchBattleRequest.getMatchId()).orElseThrow(()-> new Exception("No hay match con ese id"));
        match.battle(matchBattleRequest);
        return MatchModelResponse.toMatchModel(match);
    }
}
