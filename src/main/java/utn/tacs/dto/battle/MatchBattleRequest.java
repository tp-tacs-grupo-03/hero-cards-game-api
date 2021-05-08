package utn.tacs.dto.battle;

import utn.tacs.dto.deck.response.Attribute;

public class MatchBattleRequest {
    String matchId;
    String playerId;
    Attribute attribute;

    public MatchBattleRequest(String matchId, String playerId, Attribute attribute) {
        this.matchId = matchId;
        this.playerId = playerId;
        this.attribute = attribute;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }
}
