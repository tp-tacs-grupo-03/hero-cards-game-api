package tacs.cards.application.list;

import org.junit.jupiter.api.Test;
import utn.tacs.repositories.CardsRepository;
import utn.tacs.services.CardFinder;

import static org.mockito.Mockito.*;

class CardsListerTest {

    @Test
    void get_all_cards() {
        CardsRepository repository = mock(CardsRepository.class);
        CardFinder cardsLister = new CardFinder(null, repository);

        cardsLister.findAll(2, 100, "Strong", "ASC");
        verify(repository, atLeastOnce()).findAll();

    }
}
