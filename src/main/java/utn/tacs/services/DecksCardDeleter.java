package utn.tacs.services;

import org.springframework.stereotype.Service;
import utn.tacs.domain.Deck;
import utn.tacs.dto.deck.DeckCardDeleteRequest;
import utn.tacs.repositories.DecksRepository;

@Service
public class DecksCardDeleter {

    private DecksRepository repository;

    public DecksCardDeleter(DecksRepository repository) {
        this.repository = repository;
    }

    public void delete(DeckCardDeleteRequest deckCardDeleteRequest) throws Exception {
        final Deck deck = repository.find(deckCardDeleteRequest.getDeckId()).orElseThrow(()->new Exception("No hay deck con ese id"));
        deck.deleteCard(deckCardDeleteRequest.getCardToDelete());
        repository.update(deck);
    }
}
