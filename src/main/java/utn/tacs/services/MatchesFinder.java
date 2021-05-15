package utn.tacs.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import utn.tacs.domain.Match;
import utn.tacs.dto.match.MatchFindRequest;
import utn.tacs.dto.match.MatchModelResponse;
import utn.tacs.dto.match.MatchPagingRequest;
import utn.tacs.dto.match.PlayerStatus;
import utn.tacs.repositories.MatchesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchesFinder {

    private final MatchesRepository repository;

    public MatchesFinder(MatchesRepository repository) {
        this.repository = repository;
    }

    public MatchModelResponse find(MatchFindRequest matchFindRequest) throws Exception {
        final Match match = repository.find(matchFindRequest.getMatchId()).orElseThrow(()->new Exception("No hay match con ese id"));
        final MatchModelResponse matchModelResponse = MatchModelResponse.toMatchModel(match);
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final String player = auth.getName();
        matchModelResponse.setPlayerStatus(new PlayerStatus(match, player));
        return matchModelResponse;
    }

    public List<MatchModelResponse> findAll(MatchPagingRequest matchPagingRequest){
        final List<Match> matches = repository.findAll();
        return matches.stream().map(MatchModelResponse::toMatchModel).collect(Collectors.toList());
    }
}
