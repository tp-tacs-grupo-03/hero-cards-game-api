package tacs.cards.application.list;

import org.junit.jupiter.api.Test;
import utn.tacs.repositories.CardsRepository;
import utn.tacs.services.CardsLister;

import static org.mockito.Mockito.*;

class CardsListerTest {

    @Test
    void get_all_cards() {
        CardsRepository repository = mock(CardsRepository.class);
        CardsLister cardsLister = new CardsLister(repository);

        cardsLister.list(2, 100, "Strong", "ASC");
        verify(repository, atLeastOnce()).findAll();

    }
}
