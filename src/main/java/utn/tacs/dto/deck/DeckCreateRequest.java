package utn.tacs.dto.deck;

import utn.tacs.domain.CardId;

import java.util.List;

public class DeckCreateRequest {

    private List<CardId> cardIds;
    private String name;

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
