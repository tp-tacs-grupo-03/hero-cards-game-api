package utn.tacs.matches.domain;

import utn.tacs.cards.domain.Card;
import utn.tacs.model.responseModel.BattleModel;
import utn.tacs.model.responseModel.MatchStatusEnum;

import java.util.*;

public class Match {
    private String id;
    private Map<String, Queue<Card>> players;
    private String deck;
    private MatchStatusEnum status;
    private Date creationDate;
    private Date endDate;
    private String winnerID;
    private List<BattleModel> battles;

    public Match(Map<String, Queue<Card>> players, String deck, Date creationDate) {
        this.players = players;
        this.deck = deck;
        this.status = MatchStatusEnum.NEW;
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Queue<Card>> getPlayers() {
        return players;
    }

    public String getDeck() {
        return deck;
    }

    public MatchStatusEnum getStatus() {
        return status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getWinnerID() {
        return winnerID;
    }

    public List<BattleModel> getBattles() {
        return battles;
    }
}
