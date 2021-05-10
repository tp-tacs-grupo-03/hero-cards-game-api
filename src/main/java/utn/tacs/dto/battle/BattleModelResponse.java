package utn.tacs.dto.battle;

import utn.tacs.domain.Card;
import utn.tacs.dto.card.CardModelResponse;
import utn.tacs.dto.deck.response.Attribute;

import java.util.Map;

public class BattleModelResponse {
    Attribute attribute;
    Map<String, CardModelResponse> players;
    String winner;

    public BattleModelResponse(Attribute attribute, Map<String, CardModelResponse> players, String winner) {
        this.attribute = attribute;
        this.players = players;
        this.winner = winner;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public Map<String, CardModelResponse> getPlayers() {
        return players;
    }

    public void setPlayers(Map<String, CardModelResponse> players) {
        this.players = players;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
