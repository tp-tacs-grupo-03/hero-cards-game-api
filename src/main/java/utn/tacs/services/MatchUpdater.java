package utn.tacs.services;

import utn.tacs.domain.Match;
import utn.tacs.dto.match.MatchUpdateRequest;
import utn.tacs.repositories.MatchesRepository;

public class MatchUpdater {
    MatchesRepository repository;

    public void update(MatchUpdateRequest matchUpdateRequest) throws Exception {
        Match match = repository.find(matchUpdateRequest.getId()).orElseThrow(()-> new Exception("No hay match con ese id"));
        match.surrender(matchUpdateRequest.getPlayer());
        repository.update(match);
    }
}
