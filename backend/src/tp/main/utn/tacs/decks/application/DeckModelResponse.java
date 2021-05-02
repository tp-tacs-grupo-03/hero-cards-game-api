package utn.tacs.decks.application;

import utn.tacs.cards.domain.Card;

import java.util.List;


public class DeckModelResponse {


    public List<Card> cards;
    public String id;
    public String nombre;

    public DeckModelResponse(){}

    public DeckModelResponse(List<Card> cards, String id, String nombre) {
        this.cards = cards;
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
