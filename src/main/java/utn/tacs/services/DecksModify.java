package utn.tacs.services;

import org.springframework.stereotype.Service;
import utn.tacs.domain.Deck;
import utn.tacs.dto.deck.DeckModifyRequest;
import utn.tacs.repositories.DecksRepository;

@Service
public class DecksModify {

    private DecksRepository repository;

    public DecksModify(DecksRepository repository) {
        this.repository = repository;
    }

    public void modify(DeckModifyRequest deckModifyRequest) throws Exception {
        final Deck deck = repository.find(deckModifyRequest.getDeckId()).orElseThrow(() -> new Exception("No hay deck con ese id"));
        deck.setName(deckModifyRequest.getNewName());
        repository.update(deck);
    }
}
