package utn.tacs.matches;

import com.fasterxml.jackson.annotation.JsonInclude;
import utn.tacs.model.responseModel.BattleModel;
import utn.tacs.model.responseModel.MatchStatusEnum;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MatchModelResponse {
    private String id;
    private List<String> players;
    private String deck;
    private MatchStatusEnum status;
    private Date creationDate;
    private Date endDate;
    private String winnerID;
    private List<BattleModel> battles;

    public MatchModelResponse(String id, List<String> players, String deck, MatchStatusEnum status, Date creationDate) {
        this.id = id;
        this.players = players;
        this.deck = deck;
        this.status = status;
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public MatchStatusEnum getStatus() {
        return status;
    }

    public void setStatus(MatchStatusEnum status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getWinnerID() {
        return winnerID;
    }

    public void setWinnerID(String winnerID) {
        this.winnerID = winnerID;
    }

    public List<BattleModel> getBattles() {
        return battles;
    }

    public void setBattles(List<BattleModel> battles) {
        this.battles = battles;
    }
}
