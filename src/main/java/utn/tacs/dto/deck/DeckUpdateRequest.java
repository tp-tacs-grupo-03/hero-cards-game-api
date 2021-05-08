package utn.tacs.dto.deck;

import java.util.List;

public class DeckUpdateRequest {

    private final String deckId;
    private final String newName;
    private List<String> cards;

    public DeckUpdateRequest(String deckId, String newName, List<String> cards) {
        this.deckId = deckId;
        this.newName = newName;
        this.cards = cards;
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

    public void setCards(List<String> cards) {
        this.cards = cards;
    }
}
