package utn.tacs.decks.application;

import utn.tacs.cards.domain.CardId;

import java.util.List;


public class DeckModelResponse {


    public List<CardId> cardIds;
    public String id;
    public String name;

    public DeckModelResponse(){}

    public DeckModelResponse(List<CardId> cardIds, String id, String name) {
        this.cardIds = cardIds;
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setCards(List<CardId> cardIds) {
        this.cardIds = cardIds;
    }

    public List<CardId> getCardIds() {
        return cardIds;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
