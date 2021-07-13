package utn.tacs.dto.player;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import utn.tacs.domain.PlayerStats;

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
    private int surrenderedMatches;

    private PlayerStatsModel(String playerID, int playedMatches, int wonMatches, int lostMatches, int createdMatches, int inProgressMatches, int surrenderedMatches) {
        this.playerID = playerID;
        this.playedMatches = playedMatches;
        this.wonMatches = wonMatches;
        this.lostMatches = lostMatches;
        this.createdMatches = createdMatches;
        this.inProgressMatches = inProgressMatches;
        this.surrenderedMatches = surrenderedMatches;
    }

    static public PlayerStatsModel toPlayerStatsModel(PlayerStats playerStats){
        return new PlayerStatsModel(playerStats.getId(), playerStats.getTotalMatches(), playerStats.getWonMatches(), playerStats.getLostMatches(), playerStats.getCreatedMatches(), playerStats.getInProgressMatches(), playerStats.getSurrenderedMatches());
    }
}
