package utn.tacs.dto.deck.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ListPlayerStatsModel {

    private List<PlayerStatsModel> playerStatsModels= new ArrayList<>();

    public ListPlayerStatsModel addPlayersStatsModel(PlayerStatsModel playerStatsModel) {
        this.playerStatsModels.add(playerStatsModel);
        return this;
    }

}
