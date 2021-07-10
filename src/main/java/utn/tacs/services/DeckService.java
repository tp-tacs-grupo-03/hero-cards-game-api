package utn.tacs.services;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import utn.tacs.domain.CardId;
import utn.tacs.domain.Deck;
import utn.tacs.domain.repositories.DecksRepository;
import utn.tacs.dto.deck.*;
import utn.tacs.sorting.Sort;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeckService {

    private final DecksRepository repository;

    public DeckService(DecksRepository repository) {
        this.repository = repository;
    }

    public void delete(DeckDeleteRequest deckDeleteRequest) {
        repository.delete(deckDeleteRequest.getDeckId());
    }

    public DeckModelResponse findById(String id) throws Exception {
        return repository.find(id)
                .map(deck -> new DeckModelResponse(deck.getCardIds(), deck.getId(), deck.getName()))
                .orElseThrow(() -> new Exception("Deck no encontrado"));
    }

    public ListDeckModelResponse findAll(Pageable page, Sort sort) {
        final List<Deck> decks = repository.findAll(page, sort);
        final ListDeckModelResponse listDeckModelResponse = new ListDeckModelResponse();
        listDeckModelResponse.setDeckModelResponses(decks.stream()
                .map(deck -> new DeckModelResponse(deck.getCardIds(), deck.getId(), deck.getName()))
                .collect(Collectors.toList()));
        return listDeckModelResponse;
    }

    public DeckModelResponse create(DeckCreateRequest deckCreateRequest) {
        final Deck deck = new Deck(deckCreateRequest.getCardIds(), deckCreateRequest.getName());
        repository.save(deck);
        return new DeckModelResponse(deck.getCardIds(), deck.getId(), deck.getName());
    }

    public void update(DeckUpdateRequest deckUpdateRequest) throws Exception {
        final Deck deck = repository.find(deckUpdateRequest.getDeckId()).orElseThrow(() -> new Exception("No hay deck con ese id"));
        deck.setCardIds(deckUpdateRequest.getCards().stream().map(CardId::new).collect(Collectors.toList()));
        deck.setName(deckUpdateRequest.getNewName());
        repository.update(deck);
    }

}
