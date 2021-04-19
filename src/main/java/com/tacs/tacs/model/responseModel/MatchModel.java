package com.tacs.tacs.model.responseModel;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.validation.annotation.Validated;

@Validated
public class MatchModel {
    public String id;
    public String enemy;
    public String deck;
    public MatchStatusEnum status;
    public Date creationDate;
    public Date endDate;
    
}
