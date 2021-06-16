package utn.tacs.domain.repositories;

import utn.tacs.domain.Match;
import utn.tacs.pagination.Page;
import utn.tacs.sorting.Sort;

import java.util.List;
import java.util.Optional;

public interface MatchesRepository {

    void save(Match match);

    Optional<Match> find(String id);

    List<Match> findAll(Page page, Sort sort);

    void update(Match match);

    List<Match> findAll();
}
