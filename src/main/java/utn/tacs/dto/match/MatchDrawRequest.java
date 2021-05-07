package utn.tacs.dto.match;


public class MatchDrawRequest {

    private String matchId;
    private String playerId;

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
