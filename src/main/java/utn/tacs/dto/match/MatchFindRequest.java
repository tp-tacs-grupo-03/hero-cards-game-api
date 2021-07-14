package utn.tacs.dto.match;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MatchFindRequest {

    private String matchId;
    private boolean battle;
    private String player;
}
