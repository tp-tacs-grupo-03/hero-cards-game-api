package utn.tacs.dto.match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MatchDrawRequest {

    private String matchId;
    private String playerId;

    public MatchDrawRequest(String matchId, String playerId) {
        this.matchId = matchId;
        this.playerId = playerId;
    }
}
