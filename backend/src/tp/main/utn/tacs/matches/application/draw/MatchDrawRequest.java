package utn.tacs.matches.application.draw;

public class MatchDrawRequest {
    String matchId;
    String playerId;

    public MatchDrawRequest(String matchId, String playerId) {
        this.matchId = matchId;
        this.playerId = playerId;
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
}
