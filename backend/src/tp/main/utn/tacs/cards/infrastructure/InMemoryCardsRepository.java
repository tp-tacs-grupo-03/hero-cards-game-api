package utn.tacs.cards.infrastructure;

import org.springframework.stereotype.Service;
import utn.tacs.cards.domain.CardsRepository;
import utn.tacs.model.responseModel.CardDataModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class InMemoryCardsRepository implements CardsRepository {

    private HashMap<String, CardDataModel> cards = new HashMap<>();

    @Override
    public List<CardDataModel> findAll() {
        return new ArrayList<>(cards.values());
    }

    public void save(CardDataModel cardDataModel){
        cards.put("id", cardDataModel); //TODO terminar esto
    }

}
