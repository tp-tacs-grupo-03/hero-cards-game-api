package utn.tacs.services;

import org.springframework.stereotype.Service;
import utn.tacs.domain.Deck;
import utn.tacs.dto.deck.DeckModelResponse;
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
                .map(deck -> new DeckModelResponse(deck.getCards(), deck.getId(), deck.getName()))
                .orElseThrow(() -> new Exception("Deck no encontrado"));
    }

    public List<DeckModelResponse> findAll(){
        final List<Deck> decks = repository.findAll();
        return decks.stream().map(deck -> new DeckModelResponse(deck.getCards(), deck.getId(), deck.getName())).collect(Collectors.toList());
    }
}
