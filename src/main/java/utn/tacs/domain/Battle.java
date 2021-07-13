package utn.tacs.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utn.tacs.common.client.superHeroAPI.clientApi.SuperHeroApi;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Character;
import utn.tacs.domain.exceptions.NotFoundCharacter;
import utn.tacs.dto.card.CardModelResponse;
import utn.tacs.dto.deck.response.Attribute;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

@Getter
@NoArgsConstructor
public class Battle {

    private Attribute attribute;

    private Map<String, CardModelResponse> players;
    private String winner;

    Battle(Attribute attribute) {
        this.attribute = attribute;
        this.players = new HashMap<>();
    }

    void combat(Map<String, Queue<CardId>> players) throws Exception {
        final SuperHeroApi superHeroApi = new SuperHeroApi();
        int max = 0;
        for (Map.Entry<String, Queue<CardId>> element : players.entrySet()) {
            final CardId cardId = element.getValue().remove();
            final Character cht = superHeroApi.getCharacter(cardId.getId()).orElseThrow(()-> new NotFoundCharacter(cardId.getId()));
            final Card card = new Card(cardId, cht.getPowerstats());
            if (card.getValueOf(this.attribute) > max){
                max = card.getValueOf(this.attribute);
                this.winner = element.getKey();
            }
            this.players.put(element.getKey(), CardModelResponse.toCardModelResponse(cht));
        }
    }

}
