package utn.tacs.decks.application.addCard;

import org.springframework.stereotype.Service;
import utn.tacs.decks.domain.Deck;
import utn.tacs.decks.domain.DecksRepository;

@Service
public class DecksCardAdder {

    DecksRepository repository;

    public DecksCardAdder(DecksRepository repository) {
        this.repository = repository;
    }

    public void add(DeckAddRequest deckAddRequest) throws Exception {
        Deck deck = repository.find(deckAddRequest.getDeckId()).orElseThrow(() -> new Exception("No hay deck con ese id"));
        deck.addCards(deckAddRequest.getCard());
    }
}
