package utn.tacs.dto.match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.dto.player.PlayerStatusEnum;

@Getter
@Setter
@NoArgsConstructor
public class MatchUpdateRequest {
    private String player;
    private String id;
    private PlayerStatusEnum status;
}
