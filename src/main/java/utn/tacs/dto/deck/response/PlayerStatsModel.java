package utn.tacs.dto.deck.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@NoArgsConstructor
public class PlayerStatsModel {

    private String playerID;
    private int playedMatches;
    private int wonMatches;
    private int lostMatches;

    public PlayerStatsModel(String s, int i, int i1, int i2) {
        this.playerID = s;
        this.playedMatches = i;
        this.wonMatches = i1;
        this.lostMatches = i2;
    }
}
