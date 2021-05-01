package utn.tacs.decks.application.deleteCard;

import utn.tacs.cards.domain.Card;

public class DeckCardDeleteRequest {

    String deckId;
    Card cardToDelete;


    public DeckCardDeleteRequest(String deckId, Card card) {
        this.deckId = deckId;
        this.cardToDelete = card;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public Card getCardToDelete() {
        return cardToDelete;
    }

    public void setCardToDelete(Card cardToDelete) {
        this.cardToDelete = cardToDelete;
    }

    public String getDeckId() {
        return deckId;
    }
}
