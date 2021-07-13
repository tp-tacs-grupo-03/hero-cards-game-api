package utn.tacs.dto.player;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.domain.PlayerStats;
import utn.tacs.dto.player.PlayerStatsModel;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ListPlayerStatsModel {
    private int page;
    private int pageSize;
    private int page_count;
    private int total_count;
    private List<PlayerStats> playerStats;
}
