package utn.tacs.dto.match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MatchCreateRequest {

    private List<String> players;
    private String deck;

    public MatchCreateRequest(List<String> players, String deck) {
        this.players = players;
        this.deck = deck;
    }
}
