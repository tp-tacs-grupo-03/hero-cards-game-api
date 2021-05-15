package utn.tacs.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import utn.tacs.domain.Match;
import utn.tacs.dto.match.*;
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
        MatchModelResponse matchModelResponse = MatchModelResponse.toMatchModel(match);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String player = auth.getName();
        matchModelResponse.setPlayerStatus(new PlayerStatus(match, player));
        return matchModelResponse;
    }

    public ListMatchModelResponse findAll(MatchPagingRequest matchPagingRequest){
        final List<Match> matches = repository.findAll();
        final ListMatchModelResponse listMatchModelResponse = new ListMatchModelResponse();
        listMatchModelResponse.setMatchModelResponses(matches.stream().map(MatchModelResponse::toMatchModel).collect(Collectors.toList()));

        return listMatchModelResponse;
    }
}
