package utn.tacs.decks.application.update;

import org.springframework.stereotype.Service;
import utn.tacs.cards.domain.CardId;
import utn.tacs.decks.domain.Deck;
import utn.tacs.decks.domain.DecksRepository;

import java.util.stream.Collectors;

@Service
public class DecksUpdater {

    DecksRepository repository;

    public DecksUpdater(DecksRepository repository) {
        this.repository = repository;
    }

    public void update(DeckUpdateRequest deckUpdateRequest) throws Exception {
        Deck deck = repository.find(deckUpdateRequest.getDeckId()).orElseThrow(() -> new Exception("No hay deck con ese id"));
        deck.setCardIds(deckUpdateRequest.getCards().stream().map(CardId::new).collect(Collectors.toList()));
        deck.setName(deckUpdateRequest.getNewName());
        repository.update(deck);
    }
}
