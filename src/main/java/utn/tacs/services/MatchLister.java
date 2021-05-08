package utn.tacs.services;

import org.springframework.stereotype.Service;
import utn.tacs.domain.Match;
import utn.tacs.dto.match.MatchModelResponse;
import utn.tacs.dto.match.MatchPagingRequest;
import utn.tacs.repositories.MatchesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchLister {

    private MatchesRepository repository;

    public MatchLister(MatchesRepository repository) {
        this.repository = repository;
    }

    public List<MatchModelResponse> list(MatchPagingRequest matchPagingRequest){
        final List<Match> matches = repository.findAll();
        return matches.stream().map(MatchModelResponse::toMatchModel).collect(Collectors.toList());
    }
}
