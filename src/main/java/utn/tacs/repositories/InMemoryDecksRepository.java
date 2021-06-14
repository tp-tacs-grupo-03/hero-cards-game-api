package utn.tacs.repositories;

import org.springframework.stereotype.Service;
import utn.tacs.domain.Deck;
import utn.tacs.pagination.Page;
import utn.tacs.pagination.Pageable;
import utn.tacs.sorting.Sort;
import utn.tacs.sorting.Sortable;

import java.util.*;



public class InMemoryDecksRepository implements DecksRepository , Pageable<Deck> , Sortable {

    private HashMap<String, Deck> decks = new HashMap<>();

    @Override
    public List<Deck> findAll(Page page, Sort sort) {
        final List<Deck> sorted=  new ArrayList<>(decks.values());
        switch (sort.getSortField()) {
            case ID: sorted.sort(sort.isAsc() ? getComparatorById(): getComparatorById().reversed());
                break;
            case NAME: sorted.sort(sort.isAsc() ? getComparatorByName(): getComparatorByName().reversed());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + sort.getSortField());
        }
        return getPage(page,sorted);
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
    public Deck save(Deck deck) {
        deck.setId(UUID.randomUUID().toString());
        decks.put(deck.getId(), deck);
        return deck;
    }

    @Override
    public void update(Deck deck) {
        decks.replace(deck.getId(), deck);
    }
}
