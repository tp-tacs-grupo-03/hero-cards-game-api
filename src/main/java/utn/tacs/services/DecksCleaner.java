package utn.tacs.services;

import org.springframework.stereotype.Service;
import utn.tacs.dto.deck.DeckDeleteRequest;
import utn.tacs.repositories.DecksRepository;

@Service
public class DecksCleaner {

    private DecksRepository repository;

    public DecksCleaner(DecksRepository repository) {
        this.repository = repository;
    }

    public void delete(DeckDeleteRequest deckDeleteRequest){
        repository.delete(deckDeleteRequest.getDeckId());
    }
}
