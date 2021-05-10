package utn.tacs.dto.match;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import utn.tacs.domain.Battle;
import utn.tacs.domain.Match;
import utn.tacs.dto.deck.response.MatchStatusEnum;

import java.util.ArrayList;
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
    private List<Battle> battles;
    @Getter @Setter
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
        matchModelResponse.setBattles(match.getBattles());
        matchModelResponse.setEndDate(match.getEndDate());
        matchModelResponse.setWinnerID(match.getWinnerID());
        return matchModelResponse;
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

    public List<Battle> getBattles() {
        return battles;
    }

    public void setBattles(List<Battle> battles) {
        this.battles = battles;
    }
}
