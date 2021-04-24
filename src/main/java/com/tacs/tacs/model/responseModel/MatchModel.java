package com.tacs.tacs.model.responseModel;


import java.sql.Date;

import org.springframework.validation.annotation.Validated;

@Validated
public class MatchModel {
    public String id;
    public String enemy;
    public String deck;
    public PlayerStatusEnum statusPlayer1;
    public PlayerStatusEnum statusPlayer2;
    public MatchStatusEnum status;
    public Date creationDate;
    public Date endDate;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    
}
