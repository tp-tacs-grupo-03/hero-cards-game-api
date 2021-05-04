package utn.tacs.decks.application.modify;

import org.springframework.stereotype.Service;
import utn.tacs.decks.domain.Deck;
import utn.tacs.decks.domain.DecksRepository;

@Service
public class DecksModify {

    DecksRepository repository;

    public DecksModify(DecksRepository repository) {
        this.repository = repository;
    }

    public void modify(DeckModifyRequest deckModifyRequest) throws Exception {
        final Deck deck = repository.find(deckModifyRequest.getDeckId()).orElseThrow(() -> new Exception("No hay deck con ese id"));
        deck.setName(deckModifyRequest.getNewName());
        repository.update(deck);
    }
}
