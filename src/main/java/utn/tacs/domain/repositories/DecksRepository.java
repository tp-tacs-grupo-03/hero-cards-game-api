package utn.tacs.domain.repositories;

import org.springframework.data.domain.Pageable;
import utn.tacs.domain.Deck;
import utn.tacs.sorting.Sort;

import java.util.List;
import java.util.Optional;

public interface DecksRepository {

    List<Deck> findAll(Pageable pageable, Sort sort, String filterName);

    Optional<Deck> find(String id);

    void delete(String id);

    Deck save(Deck deck);

    void update(Deck deck);

    int getTotal();
}
