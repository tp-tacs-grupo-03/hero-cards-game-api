package utn.tacs.dto.match;

import lombok.Getter;
import lombok.Setter;
import utn.tacs.domain.Battle;
import utn.tacs.domain.CardId;
import utn.tacs.domain.Match;
import utn.tacs.dto.deck.response.MatchTypeEnum;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
public class MatchPersistModel {

    private String id;
    private Map<String, List<CardId>> players;
    private String deck;
    private MatchStatusEnum status;
    private LocalDateTime creationDate;
    private LocalDateTime endDate;
    private MatchTypeEnum type;
    private String winnerID;
    private List<Battle> battles;

    static public MatchPersistModel toMatchPersistModel(Match match){

        if (match == null){
            return null;
        }

        MatchPersistModel matchPersistModel = new MatchPersistModel();
        matchPersistModel.setBattles(match.getBattles());
        matchPersistModel.setCreationDate(match.getCreationDate());
        matchPersistModel.setDeck(match.getDeck());
        matchPersistModel.setType(match.getType());
        matchPersistModel.setId(match.getId());
        matchPersistModel.setStatus(match.getStatus());
        matchPersistModel.setEndDate(match.getEndDate());
        matchPersistModel.setWinnerID(match.getWinnerID());

        Map<String, List<CardId>> map = new HashMap<>();
        for (Map.Entry<String,  Queue<CardId>> entry : match.getPlayers().entrySet()) {
            map.put(entry.getKey(), new ArrayList<>( entry.getValue() ));
        }
        matchPersistModel.setPlayers(map);
        return matchPersistModel;
    }



    static public Match toMatch(MatchPersistModel matchPersistModel){
        if (matchPersistModel == null){
            return null;
        }
        Match match = new Match();

        match.setId(matchPersistModel.getId());
        match.setBattles(matchPersistModel.getBattles());
        match.setCreationDate(matchPersistModel.getCreationDate());
        match.setEndDate(matchPersistModel.getEndDate());
        match.setWinnerID(matchPersistModel.getWinnerID());
        match.setType(matchPersistModel.getType());
        match.setDeck(matchPersistModel.getDeck());
        match.setStatus(matchPersistModel.getStatus());

        Map<String, Queue<CardId>> map = new HashMap<>();
        for (Map.Entry<String,  List<CardId>> entry : matchPersistModel.getPlayers().entrySet()) {
            map.put(entry.getKey(), new LinkedList<>(entry.getValue()));
        }
        match.setPlayers(map);
        return match;
    }

    @Override
    public String toString() {
        return "" + players;
    }
}
