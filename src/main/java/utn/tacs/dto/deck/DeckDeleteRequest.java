package utn.tacs.dto.deck;

public class DeckDeleteRequest {

    private String deckId;

    public DeckDeleteRequest(String deckId) {
        this.deckId = deckId;
    }

    public String getDeckId() {
        return deckId;
    }
}
