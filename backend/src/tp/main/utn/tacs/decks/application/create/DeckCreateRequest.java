package utn.tacs.decks.application.create;

import utn.tacs.cards.domain.Card;
import utn.tacs.cards.domain.CardId;

import java.util.List;

public class DeckCreateRequest {

    List<CardId> cardIds;
    String name;

    public DeckCreateRequest(List<CardId> cardIds, String name) {
        this.cardIds = cardIds;
        this.name = name;
    }

    public List<CardId> getCards() {
        return cardIds;
    }

    public String getName(){
        return name;
    }
}
