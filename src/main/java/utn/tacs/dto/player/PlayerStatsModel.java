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
    private int createdMatches;
    private int inProgressMatches;
    private int surrenderMatches;

    private PlayerStatsModel(String playerID, int playedMatches, int wonMatches, int lostMatches, int createdMatches, int inProgressMatches, int surrenderMatches) {
        this.playerID = playerID;
        this.playedMatches = playedMatches;
        this.wonMatches = wonMatches;
        this.lostMatches = lostMatches;
        this.createdMatches = createdMatches;
        this.inProgressMatches = inProgressMatches;
        this.surrenderMatches = surrenderMatches;
    }

    static public PlayerStatsModel toPlayerStatsModel(PlayerStats playerStats){
        return new PlayerStatsModel(playerStats.getId(), playerStats.getTotalMatches(), playerStats.getWonMatches(), playerStats.getLostMatches(), playerStats.getCreatedMatches(), playerStats.getInProgressMatch(), playerStats.getSurrenderedMatches());
    }
}
