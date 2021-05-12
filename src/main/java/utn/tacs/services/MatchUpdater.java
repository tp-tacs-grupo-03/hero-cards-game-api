package utn.tacs.services;

import org.springframework.stereotype.Service;
import utn.tacs.domain.Match;
import utn.tacs.dto.match.MatchUpdateRequest;
import utn.tacs.repositories.MatchesRepository;

@Service
public class MatchUpdater {
    private MatchesRepository repository;

    public MatchUpdater(MatchesRepository repository){
        this.repository = repository;
    }

    public void update(MatchUpdateRequest matchUpdateRequest) throws Exception {
        final Match match = repository.find(matchUpdateRequest.getId()).orElseThrow(()-> new Exception("No hay match con ese id"));
        match.surrender(matchUpdateRequest.getPlayer());
        repository.update(match);
    }
}
