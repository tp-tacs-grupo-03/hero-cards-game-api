package utn.tacs.decks.domain;

import java.util.List;
import java.util.Optional;

public interface DecksRepository {

    List<Deck> findAll();

    Optional<Deck> find(String id);

    void delete(String id);

    void save(Deck deck);

    void update(Deck deck);

}
