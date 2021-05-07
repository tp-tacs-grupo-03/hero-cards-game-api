package utn.tacs.dto.deck.response;


import org.springframework.validation.annotation.Validated;

@Validated
public class MatchModel {

    private String opponentId;
    private String deckId;

    public MatchModel(String opponentId, String deckId) {
        this.opponentId = opponentId;
        this.deckId = deckId;
    }

    public String getOpponentId() {
        return opponentId;
    }

    public void setOpponentId(String opponentId) {
        this.opponentId = opponentId;
    }

    public String getDeckId() {
        return deckId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }


}
