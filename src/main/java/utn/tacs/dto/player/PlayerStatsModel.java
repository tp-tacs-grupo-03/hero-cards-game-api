package utn.tacs.dto.player;

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

    public PlayerStatsModel(String playerID, int playedMatches, int wonMatches, int lostMatches) {
        this.playerID = playerID;
        this.playedMatches = playedMatches;
        this.wonMatches = wonMatches;
        this.lostMatches = lostMatches;
    }
}
