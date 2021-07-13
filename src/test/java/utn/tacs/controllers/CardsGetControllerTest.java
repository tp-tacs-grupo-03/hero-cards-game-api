package utn.tacs.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import utn.tacs.TacsApplication;
import utn.tacs.domain.CardId;
import utn.tacs.domain.repositories.UsersRepository;
import utn.tacs.dto.card.CardFindRequest;
import utn.tacs.dto.card.CardModelResponse;
import utn.tacs.services.CardService;


@SpringBootTest(classes = {TacsApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
final class CardsGetControllerTest extends RequestTestCase {

    @Test
    void findCardById() throws Exception{
        assertRequest("GET","/api/cards/1", 200);
    }
}





