package utn.tacs.decks.application.deleteCard;

import utn.tacs.cards.domain.Card;
import utn.tacs.cards.domain.CardId;

public class DeckCardDeleteRequest {

    String deckId;
    CardId cardIdToDelete;


    public DeckCardDeleteRequest(String deckId, CardId cardId) {
        this.deckId = deckId;
        this.cardIdToDelete = cardId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public CardId getCardToDelete() {
        return cardIdToDelete;
    }

    public void setCardToDelete(CardId cardIdToDelete) {
        this.cardIdToDelete = cardIdToDelete;
    }

    public String getDeckId() {
        return deckId;
    }
}
