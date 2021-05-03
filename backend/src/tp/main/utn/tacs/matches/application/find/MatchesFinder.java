package utn.tacs.matches.application.find;

import org.springframework.stereotype.Service;
import utn.tacs.matches.MatchModelResponse;
import utn.tacs.matches.domain.Match;
import utn.tacs.matches.domain.MatchesRepository;

import java.util.ArrayList;

@Service
public class MatchesFinder {

    MatchesRepository repository;

    public MatchesFinder(MatchesRepository repository) {
        this.repository = repository;
    }

    public MatchModelResponse find(MatchFindRequest matchFindRequest) throws Exception {
        Match match = repository.find(matchFindRequest.getMatchId()).orElseThrow(()->new Exception("No hay match con ese id"));
        MatchModelResponse matchModelResponse = new MatchModelResponse(match.getId(), new ArrayList<>(match.getPlayers().keySet()), match.getDeck(), match.getStatus(), match.getCreationDate());
        matchModelResponse.setBattles(match.getBattles());
        matchModelResponse.setEndDate(match.getEndDate());
        matchModelResponse.setWinnerID(match.getWinnerID());

        return matchModelResponse;
    }
}
