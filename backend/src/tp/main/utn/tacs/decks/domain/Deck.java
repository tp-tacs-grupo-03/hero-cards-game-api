package utn.tacs.decks.domain;

import utn.tacs.cards.domain.Card;
import utn.tacs.cards.domain.CardId;

import java.util.*;

public class Deck {

    String id;
    List<CardId> cardIds;
    String name;

    public Deck(List<CardId> cardIds, String name) {
        this.cardIds = cardIds;
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

    public List<CardId> getCards() {
        return cardIds;
    }

    public String getName() {
        return name;
    }

    public void addCards(List<CardId> cardIds) {
        this.cardIds.addAll(cardIds);
    }

    public void deleteCard(CardId cardId){
        cardIds.remove(cardId);
    }

    public void shuffle() {
        Collections.shuffle(this.cardIds);
    }

    public List<Queue<CardId>> split(int users) {
        int module = this.cardIds.size() % users;
        int partitionSize = this.cardIds.size() / users;
        List<Queue<CardId>> partitions = new LinkedList<>();
        for (int i = 0; i < this.cardIds.size() - module; i += partitionSize) {
            partitions.add(new LinkedList<>(this.cardIds.subList(i,
                    Math.min(i + partitionSize, this.cardIds.size()))));
        }
        return partitions;
    }
}
