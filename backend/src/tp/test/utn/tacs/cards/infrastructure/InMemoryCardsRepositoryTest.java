package utn.tacs.cards.infrastructure;

import org.junit.jupiter.api.Test;
import utn.tacs.model.responseModel.CardDataModel;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryCardsRepositoryTest {

    @Test
    void save() {
        InMemoryCardsRepository repository = new InMemoryCardsRepository();
        repository.save(new CardDataModel(1, 1, 1, 1, 1, 1, 1));
    }

    @Test
    void findAll() {
        InMemoryCardsRepository repository = new InMemoryCardsRepository();
        CardDataModel cardDataModel = new CardDataModel(1, 1,1, 1, 1, 1, 1);
        repository.save(cardDataModel);
        assertEquals(1, repository.findAll().size());
    }
}