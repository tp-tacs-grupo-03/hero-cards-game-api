package utn.tacs.dto.deck;

public class DeckModifyRequest {

    private final String deckId;
    private final String newName;

    public DeckModifyRequest(String deckId, String newName) {
        this.deckId = deckId;
        this.newName = newName;
    }

    public String getDeckId() {
        return deckId;
    }

    public String getNewName() {
        return newName;
    }
}
