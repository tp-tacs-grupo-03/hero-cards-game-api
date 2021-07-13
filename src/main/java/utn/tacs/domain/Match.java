package utn.tacs.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import utn.tacs.dto.battle.MatchBattleRequest;
import utn.tacs.dto.deck.response.Attribute;
import utn.tacs.dto.deck.response.MatchTypeEnum;
import utn.tacs.dto.match.MatchStatusEnum;
import utn.tacs.dto.match.MatchUpdateRequest;
import utn.tacs.sorting.DateComparable;
import utn.tacs.sorting.IdComparable;
import utn.tacs.sorting.StatusComparable;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class Match implements IdComparable, DateComparable, StatusComparable {

    private String id;
    private Map<String, Queue<CardId>> players;
    private String deck;
    private MatchStatusEnum status;
    private MatchTypeEnum type;
    private Date creationDate;
    private Date endDate;
    private String winnerID;
    private List<Battle> battles;

    public Match(Map<String, Queue<CardId>> players, String deck, MatchTypeEnum type) {
        this.deck = deck;
        this.status = MatchStatusEnum.IN_PROGRESS;
        this.creationDate = new Date();
        this.type = type;
        this.players = players;
        this.battles = new ArrayList<>();
    }

    public CardId getNextCard(String playerId) {
        return this.players.get(playerId).peek();
    }

    public String turn(){
        return this.players.keySet().stream().sorted().collect(Collectors.toList()).get(this.battles.size() % this.players.size());
    }

    public Battle battle(MatchUpdateRequest matchUpdateRequest) throws Exception {
        final String playerId = matchUpdateRequest.getPlayer();
        if (!turn().equals(playerId)) {
            throw new Exception("No es el turno del jugador");
        }
        Battle battle = new Battle(matchUpdateRequest.getAttribute());
        battle.combat(players);
        battles.add(battle);
        if(this.type == MatchTypeEnum.TRAINING && cardLeft() > 0){
            Battle battleBot = this.combatBot();
            battles.add(battleBot);
        }
        calculateWinner();
        return battle;
    }

    private Battle combatBot() throws Exception {
        Battle battle = new Battle();
        battle.combatBot(players);
        return battle;
    }

    private void calculateWinner(){
        if (cardLeft() == 0){
            this.winnerID = Collections.max(wins().entrySet(), Comparator.comparingLong(Map.Entry::getValue)).getKey();
            this.status = MatchStatusEnum.FINISHED;
        }
    }

    private Map<String, Long> wins(){
        return this.battles.stream()
                .collect(Collectors.groupingBy(Battle::getWinner,
                        Collectors.counting()));
    }

    public void surrender(String player){
        setStatus(MatchStatusEnum.CANCELED);
        calculateWinner();
    }

    public int cardLeft() {
        return players.entrySet().iterator().next().getValue().size();
    }

    public boolean isTerminated() {
        return this.status.equals(MatchStatusEnum.FINISHED) || this.status.equals(MatchStatusEnum.CANCELED);
    }

    public boolean isPlayer(String player) {
        return this.players.containsKey(player);
    }

    @Override
    public long getDate() {
        return this.creationDate.getTime();
    }
}
