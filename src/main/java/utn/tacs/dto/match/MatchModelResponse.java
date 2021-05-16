package utn.tacs.dto.match;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.domain.Battle;
import utn.tacs.domain.Match;
import utn.tacs.dto.deck.response.MatchStatusEnum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
public class MatchModelResponse {

    private String id;
    private List<String> players;
    private String deck;
    private MatchStatusEnum status;
    private Date creationDate;
    private Date endDate;
    private String winnerID;
    private int cardsLeft;
    private String turn;
    private PlayerStatus playerStatus;

    public MatchModelResponse(String id, List<String> players, String deck, MatchStatusEnum status, Date creationDate) {
        this.id = id;
        this.players = players;
        this.deck = deck;
        this.status = status;
        this.creationDate = creationDate;
    }

    static public MatchModelResponse toMatchModel(Match match){
        MatchModelResponse matchModelResponse = new MatchModelResponse(match.getId(), new ArrayList<>(match.getPlayers().keySet()), match.getDeck(), match.getStatus(), match.getCreationDate());
        matchModelResponse.setEndDate(match.getEndDate());
        matchModelResponse.setWinnerID(match.getWinnerID());
        matchModelResponse.setCardsLeft(match.cardLeft());
        return matchModelResponse;
    }
}
