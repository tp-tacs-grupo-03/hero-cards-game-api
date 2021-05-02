package utn.tacs.decks.domain;

import utn.tacs.cards.domain.Card;

import java.util.*;

public class Deck {

    String id;
    List<Card> cards;
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

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public List<Queue<Card>> split(int users) {
        int module = this.cards.size() % users;
        int partitionSize = this.cards.size() / users;
        List<Queue<Card>> partitions = new LinkedList<>();
        for (int i = 0; i < this.cards.size() - module; i += partitionSize) {
            partitions.add(new LinkedList<>(this.cards.subList(i,
                    Math.min(i + partitionSize, this.cards.size()))));
        }
        return partitions;
    }
}
