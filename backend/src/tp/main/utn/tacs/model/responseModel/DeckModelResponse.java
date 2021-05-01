package utn.tacs.model.responseModel;

import utn.tacs.cards.domain.Card;

import java.util.List;


public class DeckModelResponse {


    public List<Card> cards;
    public String id;
    public String nombre;

    public DeckModelResponse(List<Card> cards, String id, String nombre) {
        this.cards = cards;
        this.id = id;
        this.nombre = nombre;
    }
}
