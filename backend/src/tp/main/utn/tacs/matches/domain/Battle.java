package utn.tacs.matches.domain;

import utn.tacs.cards.domain.Card;
import utn.tacs.cards.domain.CardId;
import utn.tacs.model.responseModel.Attribute;
import utn.tacs.superHeroAPI.clientApi.SuperHeroApi;
import utn.tacs.superHeroAPI.clientApi.model.Powerstats;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Battle {
    private Attribute attribute;
    private Map<String, Card> players;
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
            Powerstats powerstats = superHeroApi.getPowerstats(cardId.getId()).orElseThrow(()-> new Exception("No responde la base datos")).getBody();
            Card card = new Card(cardId, powerstats);
            if (card.getValueOf(this.attribute) > max){
                max = card.getValueOf(this.attribute);
                this.winner = element.getKey();
            }
            this.players.put(element.getKey(), card);
        }
    }

    public String getWinner(){
        return winner;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public Map<String, Card> getPlayers() {
        return players;
    }
}
