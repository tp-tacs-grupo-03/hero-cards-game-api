package com.tacs.tacs.model.requestModel;

import java.util.List;
import com.tacs.tacs.model.responseModel.CardModel;

import org.springframework.lang.NonNull;

public class DeckModel {
    @NonNull
    public List<CardModel> cards;
    public String id;
    public String nombre;
    
}
