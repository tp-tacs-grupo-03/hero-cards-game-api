package utn.tacs.repositories;

import org.springframework.stereotype.Service;
import utn.tacs.domain.Deck;

import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
public class InMemoryDecksRepository implements DecksRepository {

    private HashMap<String, Deck> decks = new HashMap<>();

    @Override
    public List<Deck> findAll() {
        return new ArrayList<>(decks.values());
    }

    @Override
    public Optional<Deck> find(String id) {
        return Optional.ofNullable(decks.get(id));
    }

    @Override
    public void delete(String id) {
        decks.remove(id);
    }

    @Override
    public void save(Deck deck) {
        deck.setId(UUID.randomUUID().toString());
        decks.put(deck.getId(), deck);
    }

    @Override
    public void update(Deck deck) {
        decks.replace(deck.getId(), deck);
    }
}