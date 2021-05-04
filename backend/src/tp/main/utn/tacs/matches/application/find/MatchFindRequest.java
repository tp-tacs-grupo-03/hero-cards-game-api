package utn.tacs.matches.application.find;

public class MatchFindRequest {
    private String matchId;

    public MatchFindRequest(String matchId) {
        this.matchId = matchId;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }
}
