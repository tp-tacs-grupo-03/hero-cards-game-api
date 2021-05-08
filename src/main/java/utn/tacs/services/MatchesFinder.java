package utn.tacs.services;

import org.springframework.stereotype.Service;
import utn.tacs.domain.Match;
import utn.tacs.dto.match.MatchFindRequest;
import utn.tacs.dto.match.MatchModelResponse;
import utn.tacs.repositories.MatchesRepository;

@Service
public class MatchesFinder {

    private MatchesRepository repository;

    public MatchesFinder(MatchesRepository repository) {
        this.repository = repository;
    }

    public MatchModelResponse find(MatchFindRequest matchFindRequest) throws Exception {
        final Match match = repository.find(matchFindRequest.getMatchId()).orElseThrow(()->new Exception("No hay match con ese id"));
        return MatchModelResponse.toMatchModel(match);
    }
}
