package utn.tacs.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import utn.tacs.TacsApplication;
import utn.tacs.domain.CardId;
import utn.tacs.dto.card.CardFindRequest;
import utn.tacs.dto.card.CardModelResponse;
import utn.tacs.services.CardService;


@SpringBootTest(classes = {TacsApplication.class})
final class CardsGetControllerTest extends RequestTestCase {

    @MockBean
    private CardService cardService;

    @Test
    void findCardById() throws Exception{
        Mockito.when(cardService.find(new CardFindRequest(new CardId("1"))))
                .thenReturn(new CardModelResponse());

        assertRequest("GET","/api/cards/1", 200);
    }
}





