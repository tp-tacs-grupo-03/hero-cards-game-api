package utn.tacs.decks.application.addCard;

import utn.tacs.cards.domain.Card;
import utn.tacs.cards.domain.CardId;

import java.util.List;

public class DeckAddRequest {
    private String deckId;
    private List<CardId> cardIds;

    public DeckAddRequest(String deckId, List<CardId> cardIds) {
        this.deckId = deckId;
        this.cardIds = cardIds;
    }

    public String getDeckId() {
        return deckId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public List<CardId> getCard() {
        return cardIds;
    }

    public void setCards(List<CardId> cardIds) {
        this.cardIds = cardIds;
    }
}
