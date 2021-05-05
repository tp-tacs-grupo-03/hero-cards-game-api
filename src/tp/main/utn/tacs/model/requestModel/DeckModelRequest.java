package utn.tacs.model.requestModel;

import java.util.List;


public class DeckModelRequest {

    public List<String> cards;
    public String name;

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
