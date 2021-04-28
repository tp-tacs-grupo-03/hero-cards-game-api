package com.tacs.tacs.model.responseModel;

public class BattleModel {
    private String id;
    private String attribute;
    private CardDataModel cardPlayer1;
    private CardDataModel cardPlayer2;

    public int getWinner(){
        return 1;
    };
}
