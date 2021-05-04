package utn.tacs.cards;

public class CardModelResponse {
    String cardId;

    public CardModelResponse(String cardId) {
        this.cardId = cardId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
