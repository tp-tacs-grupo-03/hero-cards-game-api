package com.tacs.tacs.model.requestModel;

import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

@Validated
public class CardModel {
    @NonNull
    public int id;
}
