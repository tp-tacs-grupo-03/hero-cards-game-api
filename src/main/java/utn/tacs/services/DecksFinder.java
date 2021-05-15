package utn.tacs.services;

import org.springframework.stereotype.Service;
import utn.tacs.domain.Deck;
import utn.tacs.dto.deck.DeckModelResponse;
import utn.tacs.dto.deck.ListDeckModelResponse;
import utn.tacs.repositories.DecksRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DecksFinder {

    private DecksRepository repository;

    public DecksFinder(DecksRepository repository) {
        this.repository = repository;
    }

    public DeckModelResponse findById(String id) throws Exception {
        return repository.find(id)
                .map(deck -> new DeckModelResponse(deck.getCardIds(), deck.getId(), deck.getName()))
                .orElseThrow(() -> new Exception("Deck no encontrado"));
    }

    public ListDeckModelResponse findAll(){
        final List<Deck> decks = repository.findAll();
        final ListDeckModelResponse listDeckModelResponse = new ListDeckModelResponse();
        listDeckModelResponse.setDeckModelResponses(decks.stream()
                .map(deck -> new DeckModelResponse(deck.getCardIds(), deck.getId(), deck.getName()))
                .collect(Collectors.toList()));
        return listDeckModelResponse;
    }
}
