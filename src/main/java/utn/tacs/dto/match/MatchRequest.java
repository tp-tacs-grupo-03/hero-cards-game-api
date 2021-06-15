package utn.tacs.dto.match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.dto.player.PlayerStatusEnum;

@Getter
@Setter
@NoArgsConstructor
public class MatchRequest {
    private PlayerStatusEnum status;
}
