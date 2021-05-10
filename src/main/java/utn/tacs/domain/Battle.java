package utn.tacs.domain;

import utn.tacs.common.client.superHeroAPI.clientApi.SuperHeroApi;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Character;
import utn.tacs.dto.card.CardModelResponse;
import utn.tacs.dto.deck.response.Attribute;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Battle {
    private Attribute attribute;

    private Map<String, CardModelResponse> players;
    private String winner;



    public Battle(Attribute attribute) {
        this.attribute = attribute;
        this.players = new HashMap<>();
    }

    public void combat(Map<String, Queue<CardId>> players) throws Exception {
        SuperHeroApi superHeroApi = new SuperHeroApi();
        int max = 0;
        for (Map.Entry<String, Queue<CardId>> element : players.entrySet()
             ) {
            CardId cardId = element.getValue().remove();
            Character character = superHeroApi.getCharacter(cardId.getId()).getBody();
            Card card = new Card(cardId, character.getPowerstats());
            if (card.getValueOf(this.attribute) > max){
                max = card.getValueOf(this.attribute);
                this.winner = element.getKey();
            }
            this.players.put(element.getKey(), CardModelResponse.toCardModelResponse(character));
        }
    }

    public String getWinner(){
        return winner;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public Map<String, CardModelResponse> getPlayers() {
        return players;
    }
}
