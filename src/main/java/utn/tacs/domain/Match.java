package utn.tacs.domain;

import utn.tacs.dto.battle.MatchBattleRequest;
import utn.tacs.dto.deck.response.MatchStatusEnum;

import java.util.*;
import java.util.stream.Collectors;

public class Match {
    private String id;
    private Map<String, Queue<CardId>> players;
    private String deck;
    private MatchStatusEnum status;
    private Date creationDate;
    private Date endDate;
    private String winnerID;
    private List<Battle> battles;

    public Match(Map<String, Queue<CardId>> players, String deck, Date creationDate) {
        this.players = players;
        this.deck = deck;
        this.status = MatchStatusEnum.NEW;
        this.creationDate = creationDate;
        battles = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Queue<CardId>> getPlayers() {
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

    public CardId getNextCard(String playerId) {
        return this.players.get(playerId).peek();
    }

    public Battle battle(MatchBattleRequest matchBattleRequest) throws Exception {
        String playerId = matchBattleRequest.getPlayerId();
        String turnId = this.players.keySet().stream().sorted().collect(Collectors.toList()).get(this.battles.size()%this.players.size());
        if (!playerId.equals(turnId)) {
            throw new Exception("No es el turno del jugador");
        }
        Battle battle = new Battle(matchBattleRequest.getAttribute());
        battle.combat(players);
        battles.add(battle);
        return battle;
    }
}
