package utn.tacs.repositories;

import utn.tacs.domain.Match;

import java.util.List;
import java.util.Optional;

public interface MatchesRepository {

    void save(Match match);

    Optional<Match> find(String id);

    List<Match> findAll();

    void update(Match match);
}
