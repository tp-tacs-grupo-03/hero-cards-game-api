package utn.tacs.services;

import org.springframework.stereotype.Service;
import utn.tacs.dto.deck.DeckModelResponse;
import utn.tacs.repositories.DecksRepository;

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
}
