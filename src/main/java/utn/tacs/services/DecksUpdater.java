package utn.tacs.services;

import org.springframework.stereotype.Service;
import utn.tacs.domain.CardId;
import utn.tacs.domain.Deck;
import utn.tacs.dto.deck.DeckUpdateRequest;
import utn.tacs.repositories.DecksRepository;

import java.util.stream.Collectors;

@Service
public class DecksUpdater {

    private DecksRepository repository;

    public DecksUpdater(DecksRepository repository) {
        this.repository = repository;
    }

    public void update(DeckUpdateRequest deckUpdateRequest) throws Exception {
        final Deck deck = repository.find(deckUpdateRequest.getDeckId()).orElseThrow(() -> new Exception("No hay deck con ese id"));
        deck.setCardIds(deckUpdateRequest.getCards().stream().map(CardId::new).collect(Collectors.toList()));
        deck.setName(deckUpdateRequest.getNewName());
        repository.update(deck);
    }
}
