package utn.tacs.decks.application.modify;

import java.util.List;

public class DeckModifyRequest {

    private String deckId;
    private String newName;
    private List<String> cards;

    public DeckModifyRequest(String deckId, String newName) {
        this.deckId = deckId;
        this.newName = newName;
    }

    public DeckModifyRequest(String deckId, String newName, List<String> cards) {
        this.deckId = deckId;
        this.cards = cards;
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
