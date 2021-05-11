package utn.tacs.dto.battle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.domain.Card;
import utn.tacs.dto.deck.response.Attribute;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class BattleModelResponse {

    private Attribute attribute;
    private Map<String, Card> players;
    private String winner;

    public BattleModelResponse(Attribute attribute, Map<String, Card> players, String winner) {
        this.attribute = attribute;
        this.players = players;
        this.winner = winner;
    }
}
