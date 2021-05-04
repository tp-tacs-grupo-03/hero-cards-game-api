package utn.tacs.matches.application.find;

import org.springframework.stereotype.Service;
import utn.tacs.matches.MatchModelResponse;
import utn.tacs.matches.domain.Match;
import utn.tacs.matches.domain.MatchesRepository;

@Service
public class MatchesFinder {

    MatchesRepository repository;

    public MatchesFinder(MatchesRepository repository) {
        this.repository = repository;
    }

    public MatchModelResponse find(MatchFindRequest matchFindRequest) throws Exception {
        Match match = repository.find(matchFindRequest.getMatchId()).orElseThrow(()->new Exception("No hay match con ese id"));
        return MatchModelResponse.toMatchModel(match);
    }
}
