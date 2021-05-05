package utn.tacs.decks.application.delete;

import org.springframework.stereotype.Service;
import utn.tacs.decks.domain.DecksRepository;
@Service
public class DecksDeleter {

    DecksRepository repository;

    public DecksDeleter(DecksRepository repository) {
        this.repository = repository;
    }

    public void delete(DeckDeleteRequest deckDeleteRequest){
        repository.delete(deckDeleteRequest.getDeckId());
    }
}
