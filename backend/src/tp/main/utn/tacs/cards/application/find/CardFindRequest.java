package utn.tacs.cards.application.find;

import utn.tacs.cards.domain.CardId;

public class CardFindRequest {
    private CardId cardId;

    public CardFindRequest(CardId cardId) {
        this.cardId = cardId;
    }

    public CardId getCardId() {
        return cardId;
    }

    public void setCardId(CardId cardId) {
        this.cardId = cardId;
    }
}
