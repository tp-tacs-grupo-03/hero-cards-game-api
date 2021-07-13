package utn.tacs.dto.match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.dto.deck.response.MatchTypeEnum;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MatchCreateRequest {

    private String host;
    private String opponent;
    private String deck;
    private MatchTypeEnum type;

    public MatchCreateRequest(String opponent, String deck, String hostId, MatchTypeEnum type) {
        this.opponent = opponent;
        this.deck = deck;
        this.host = hostId;
        this.type = type;
    }
}
