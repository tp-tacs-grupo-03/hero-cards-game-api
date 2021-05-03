package utn.tacs.decks.application;

import utn.tacs.cards.domain.Card;
import utn.tacs.cards.domain.CardId;

import java.util.List;


public class DeckModelResponse {


    public List<CardId> cardIds;
    public String id;
    public String nombre;

    public DeckModelResponse(){}

    public DeckModelResponse(List<CardId> cardIds, String id, String nombre) {
        this.cardIds = cardIds;
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setCards(List<CardId> cardIds) {
        this.cardIds = cardIds;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
