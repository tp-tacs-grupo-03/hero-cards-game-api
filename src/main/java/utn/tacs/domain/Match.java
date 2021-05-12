package utn.tacs.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.dto.battle.MatchBattleRequest;
import utn.tacs.dto.deck.response.MatchStatusEnum;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
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

    public CardId getNextCard(String playerId) {
        return this.players.get(playerId).peek();
    }

    public boolean turn(String playerId){
        return this.players.keySet().stream().sorted().collect(Collectors.toList()).get(this.battles.size() % this.players.size()).equals(playerId);
    }

    public Battle battle(MatchBattleRequest matchBattleRequest) throws Exception {
        String playerId = matchBattleRequest.getPlayerId();
        if (turn(playerId)) {
            throw new Exception("No es el turno del jugador");
        }
        Battle battle = new Battle(matchBattleRequest.getAttribute());
        battle.combat(players);
        battles.add(battle);
        return battle;
    }

    public Match surrender(String player){
        setStatus(MatchStatusEnum.CANCELED);
        //setWinnerID(); #TODO
        return this;
    }

    public int cardLeft(String playerId) {
        return players.get(playerId).size();
    }
}
