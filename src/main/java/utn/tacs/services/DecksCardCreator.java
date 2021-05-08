package utn.tacs.services;

import org.springframework.stereotype.Service;
import utn.tacs.domain.Deck;
import utn.tacs.dto.deck.DeckAddRequest;
import utn.tacs.repositories.DecksRepository;

@Service
public class DecksCardCreator {

    private DecksRepository repository;

    public DecksCardCreator(DecksRepository repository) {
        this.repository = repository;
    }

    public void add(DeckAddRequest deckAddRequest) throws Exception {
        final Deck deck = repository.find(deckAddRequest.getDeckId()).orElseThrow(() -> new Exception("No hay deck con ese id"));
        deck.addCards(deckAddRequest.getCard());
    }
}
