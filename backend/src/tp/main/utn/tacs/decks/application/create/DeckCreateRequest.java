package utn.tacs.decks.application.create;

import utn.tacs.cards.domain.Card;

import java.util.List;

public class DeckCreateRequest {

    List<Card> cards;
    String name;

    public DeckCreateRequest(List<Card> cards, String name) {
        this.cards = cards;
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getName(){
        return name;
    }
}
