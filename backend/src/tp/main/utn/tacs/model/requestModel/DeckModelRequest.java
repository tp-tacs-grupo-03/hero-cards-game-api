package utn.tacs.model.requestModel;

import java.util.List;


public class DeckModelRequest {
    public List<String> cards;
    public String nombre;

    public List<String> getCards() {
        return cards;
    }

    public void setCards(List<String> cards) {
        this.cards = cards;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
