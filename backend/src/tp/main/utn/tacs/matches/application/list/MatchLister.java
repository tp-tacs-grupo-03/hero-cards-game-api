package utn.tacs.matches.application.list;

import org.springframework.stereotype.Service;
import utn.tacs.matches.MatchModelResponse;
import utn.tacs.matches.domain.Match;
import utn.tacs.matches.domain.MatchesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchLister {

    MatchesRepository repository;

    public MatchLister(MatchesRepository repository) {
        this.repository = repository;
    }

    public List<MatchModelResponse> list(MatchPagingRequest matchPagingRequest){
        List<Match> matches = repository.findAll();
        return matches.stream().map(MatchModelResponse::toMatchModel).collect(Collectors.toList());
    }
}
