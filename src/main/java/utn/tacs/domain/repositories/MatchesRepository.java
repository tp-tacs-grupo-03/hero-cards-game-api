package utn.tacs.domain.repositories;

import org.springframework.data.domain.Pageable;
import utn.tacs.domain.Match;
import utn.tacs.sorting.Sort;

import java.util.List;
import java.util.Optional;

public interface MatchesRepository {

    void save(Match match);

    Optional<Match> find(String id);

    List<Match> findAll(Pageable pageable, Sort sort);

    List<Match> findAllById(Pageable pageable, Sort sort, String playerID);

    void update(Match match);

    List<Match> findAll();

    int getTotal(String playerID);
}
