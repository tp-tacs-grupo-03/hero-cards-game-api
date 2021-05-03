package utn.tacs.matches.domain;

import java.util.Optional;

public interface MatchesRepository {

    void save(Match match);

    Optional<Match> find(String id);
}
