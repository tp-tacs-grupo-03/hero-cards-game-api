package utn.tacs.model.responseModel;


import org.springframework.validation.annotation.Validated;

import java.sql.Date;
import java.util.List;

@Validated
public class MatchModel {

    private String id;
    private String player1ID;
    private String player2ID;
    private String deck;
    private PlayerStatusEnum statusPlayer1;
    private PlayerStatusEnum statusPlayer2;
    private MatchStatusEnum status;
    private Date creationDate;
    private Date endDate;
    private String winnerID;
    private List<BattleModel> battles;

    public MatchModel(String player1ID, String player2ID, String deck, PlayerStatusEnum statusPlayer1, PlayerStatusEnum statusPlayer2, MatchStatusEnum status, Date creationDate, Date endDate) {
        this.player1ID = player1ID;
        this.player2ID = player2ID;
        this.deck = deck;
        this.statusPlayer1 = statusPlayer1;
        this.statusPlayer2 = statusPlayer2;
        this.status = status;
        this.creationDate = creationDate;
        this.endDate = endDate;
    }

    public List<BattleModel> getBattles() {
        return battles;
    }

    public void setBattles(List<BattleModel> battles) {
        this.battles = battles;
    }

    public String getWinnerID() {
        return winnerID;
    }

    public void setWinnerID(String winnerID) {
        this.winnerID = winnerID;
    }

    public String getPlayer1ID() {
        return player1ID;
    }

    public void setPlayer1ID(String player1ID) {
        this.player1ID = player1ID;
    }

    public String getPlayer2ID() {
        return player2ID;
    }

    public void setPlayer2ID(String player2ID) {
        this.player2ID = player2ID;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public PlayerStatusEnum getStatusPlayer1() {
        return statusPlayer1;
    }

    public void setStatusPlayer1(PlayerStatusEnum statusPlayer1) {
        this.statusPlayer1 = statusPlayer1;
    }

    public PlayerStatusEnum getStatusPlayer2() {
        return statusPlayer2;
    }

    public void setStatusPlayer2(PlayerStatusEnum statusPlayer2) {
        this.statusPlayer2 = statusPlayer2;
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



    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    
}
