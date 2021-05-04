package utn.tacs.matches.application.create;

import java.util.List;

public class MatchCreateRequest {

    private List<String> players;
    private String deck;

    public MatchCreateRequest(List<String> players, String deck) {
        this.players = players;
        this.deck = deck;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

}
