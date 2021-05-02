package utn.tacs.decks.application.find;

import org.springframework.stereotype.Service;
import utn.tacs.decks.domain.DecksRepository;
import utn.tacs.model.responseModel.DeckModelResponse;

@Service
public class DecksFinder {

    DecksRepository repository;

    public DecksFinder(DecksRepository repository) {
        this.repository = repository;
    }

    public DeckModelResponse findById(String id) throws Exception {
        return repository.find(id)
                .map(deck -> new DeckModelResponse(deck.getCards(), deck.getId(), deck.getName()))
                .orElseThrow(() -> new Exception("Deck no encontrado"));
    }
}
