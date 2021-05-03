package utn.tacs.matches.domain;

import utn.tacs.cards.domain.Card;
import utn.tacs.model.responseModel.Attribute;
import utn.tacs.superHeroAPI.clientApi.SuperHeroApi;
import utn.tacs.superHeroAPI.clientApi.model.Powerstats;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Battle {
    private String id;
    private Attribute attribute;
    private Map<String, Card> players;
    private String winner;



    public Battle(Attribute attribute) {

    }

    public int getWinner(){
        return 1;
    }

    public String combat(Map<String, Queue<Card>> players){
        SuperHeroApi superHeroApi = new SuperHeroApi();
        players.forEach((key, value) -> {
            this.players.put(key, value.poll());
        });
        Map<String, Powerstats> copy = new HashMap<>();


        return "";
    }
}
