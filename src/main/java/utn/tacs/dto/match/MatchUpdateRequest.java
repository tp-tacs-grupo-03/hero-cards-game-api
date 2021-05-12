package utn.tacs.dto.match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MatchUpdateRequest {
    private String player;
    private String id;
    private String status;
}
