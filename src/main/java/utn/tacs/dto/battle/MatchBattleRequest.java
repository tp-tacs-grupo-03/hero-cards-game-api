package utn.tacs.dto.battle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.dto.deck.response.Attribute;

@Getter
@Setter
@NoArgsConstructor
public class MatchBattleRequest {

    private String matchId;
    private String playerId;
    private Attribute attribute;

    public MatchBattleRequest(String matchId, String playerId, Attribute attribute) {
        this.matchId = matchId;
        this.playerId = playerId;
        this.attribute = attribute;
    }
}
