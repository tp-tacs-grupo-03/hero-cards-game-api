package utn.tacs.matches.domain;

import utn.tacs.cards.domain.Card;
import utn.tacs.matches.application.battle.MatchBattleRequest;
import utn.tacs.model.responseModel.MatchStatusEnum;

import java.util.*;
import java.util.stream.Collectors;

public class Match {
    private String id;
    private Map<String, Queue<Card>> players;
    private String deck;
    private MatchStatusEnum status;
    private Date creationDate;
    private Date endDate;
    private String winnerID;
    private List<Battle> battles;

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

    public List<Battle> getBattles() {
        return battles;
    }

    public Card getNextCard(String playerId) {
        return this.players.get(playerId).peek();
    }

    public Battle battle(MatchBattleRequest matchBattleRequest) throws Exception {
        String playerId = matchBattleRequest.getPlayerId();
        String turnId = this.players.keySet().stream().sorted().collect(Collectors.toList()).get(this.battles.size()%this.players.size());
        if (!playerId.equals(turnId)) {
            throw new Exception("No es el turno del jugador");
        }
        return new Battle(matchBattleRequest.getAttribute());
    }
}
