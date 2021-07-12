package utn.tacs.dto.match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MatchFindRequest {

    private String matchId;
    private boolean battle;

    public MatchFindRequest(String matchId, boolean battle) {
        this.matchId = matchId;
        this.battle = battle;
    }
}
