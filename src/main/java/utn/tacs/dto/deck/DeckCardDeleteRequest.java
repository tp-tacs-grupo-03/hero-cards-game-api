package utn.tacs.dto.deck;

import utn.tacs.domain.CardId;

public class DeckCardDeleteRequest {

    private String deckId;
    private CardId cardIdToDelete;


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
