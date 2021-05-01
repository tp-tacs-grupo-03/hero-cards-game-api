package utn.tacs.decks.application.deleteCard;

import org.springframework.stereotype.Service;
import utn.tacs.decks.domain.Deck;
import utn.tacs.decks.domain.DecksRepository;

@Service
public class DecksCardDeleter {

    DecksRepository repository;

    public DecksCardDeleter(DecksRepository repository) {
        this.repository = repository;
    }

    public void delete(DeckCardDeleteRequest deckCardDeleteRequest) throws Exception {
        Deck deck = repository.find(deckCardDeleteRequest.getDeckId()).orElseThrow(()->new Exception("No hay deck con ese id"));
        deck.deleteCard(deckCardDeleteRequest.getCardToDelete());
        repository.update(deck);
    }
}
