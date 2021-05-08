package utn.tacs.dto.deck;

import java.util.List;


public class DeckModelRequest {

    private List<String> cards;
    private String name;

    public List<String> getCards() {
        return cards;
    }

    public void setCards(List<String> cardIds) {
        this.cards = cardIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
