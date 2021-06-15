package tacs.cards.application.list;

import org.junit.jupiter.api.Test;
import utn.tacs.domain.repositories.CardsRepository;
import utn.tacs.services.CardService;

import static org.mockito.Mockito.*;

class CardsListerTest {

    @Test
    void getAllCards() {
        CardsRepository repository = mock(CardsRepository.class);
        CardService cardsLister = new CardService(null, repository);

        cardsLister.findAll(2, 100, "Strong", "ASC");
        verify(repository, atLeastOnce()).findAll();

    }
}
