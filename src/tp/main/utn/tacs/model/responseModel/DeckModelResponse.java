package utn.tacs.model.responseModel;

import java.util.List;


public class DeckModelResponse {


    public List<Integer> cards;
    public int id;
    public String nombre;

    public DeckModelResponse(List<Integer> cards, int id, String nombre) {
        this.cards = cards;
        this.id = id;
        this.nombre = nombre;
    }
}
