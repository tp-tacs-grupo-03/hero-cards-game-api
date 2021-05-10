package utn.tacs.dto.battle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.domain.Card;
import utn.tacs.dto.card.CardModelResponse;
import utn.tacs.dto.deck.response.Attribute;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class BattleModelResponse {

    Attribute attribute;
    Map<String, CardModelResponse> players;
    String winner;

    public BattleModelResponse(Attribute attribute, Map<String, CardModelResponse> players, String winner) {
        this.attribute = attribute;
        this.players = players;
        this.winner = winner;
    }
}
