package utn.tacs.repositories;

import utn.tacs.dto.deck.response.CardDataModel;

import java.util.List;

public interface CardsRepository {

    List<CardDataModel> findAll();

    void save(CardDataModel cardDataModel);

}
