package utn.tacs.dto.match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MatchFindRequest {

    private String matchId;

    public MatchFindRequest(String matchId) {
        this.matchId = matchId;
    }
}
