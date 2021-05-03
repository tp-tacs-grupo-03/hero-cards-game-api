package utn.tacs.decks.application.modify;

import java.util.List;

public class DeckModifyRequest {

    private final String deckId;
    private final String newName;
    private List<String> cards;

    public DeckModifyRequest(String deckId, String newName) {
        this.deckId = deckId;
        this.newName = newName;
    }

    public DeckModifyRequest(String deckId, String newName, List<String> cardIds) {
        this.deckId = deckId;
        this.cards = cardIds;
        this.newName = newName;
    }

    public String getDeckId() {
        return deckId;
    }

    public String getNewName() {
        return newName;
    }

    public List<String> getCards() {
        return cards;
    }
}
