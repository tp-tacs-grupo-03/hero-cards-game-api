package utn.tacs.repositories;

import utn.tacs.domain.Deck;
import utn.tacs.pagination.Page;
import utn.tacs.pagination.Pageable;

import java.util.List;
import java.util.Optional;

public interface DecksRepository {

    List<Deck> findAll(Page page);

    Optional<Deck> find(String id);

    void delete(String id);

    void save(Deck deck);

    void update(Deck deck);

}
