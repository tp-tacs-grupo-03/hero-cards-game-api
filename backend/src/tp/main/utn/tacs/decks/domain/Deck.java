package utn.tacs.decks.domain;

import utn.tacs.cards.domain.Card;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    String id;
    List<Card> cards = new ArrayList<>();
    String name;

    public Deck(List<Card> cards, String name) {
        this.cards = cards;
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public void deleteCard(Card card){
        cards.remove(card);
    }
}
