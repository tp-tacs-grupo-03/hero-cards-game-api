package utn.tacs.cards.infrastructure;

import utn.tacs.cards.domain.CardsRepository;
import utn.tacs.model.responseModel.CardDataModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryCardsRepository implements CardsRepository {

    private HashMap<String, CardDataModel> cards = new HashMap<>();
    @Override
    public List<CardDataModel> findAll() {
        return new ArrayList<>(cards.values());
    }
}
