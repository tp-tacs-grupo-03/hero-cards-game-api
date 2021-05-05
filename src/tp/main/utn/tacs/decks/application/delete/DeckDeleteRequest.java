package utn.tacs.decks.application.delete;

public class DeckDeleteRequest {

    String deckId;

    public DeckDeleteRequest(String deckId) {
        this.deckId = deckId;
    }

    public String getDeckId() {
        return deckId;
    }
}
