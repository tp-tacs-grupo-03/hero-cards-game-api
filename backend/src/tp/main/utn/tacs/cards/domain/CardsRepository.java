package utn.tacs.cards.domain;

import utn.tacs.model.responseModel.CardDataModel;

import java.util.List;

public interface CardsRepository {
    List<CardDataModel> findAll();
}
