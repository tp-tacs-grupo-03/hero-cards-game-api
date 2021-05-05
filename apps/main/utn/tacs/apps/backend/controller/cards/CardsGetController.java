package utn.tacs.apps.backend.controller.cards;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import utn.tacs.cards.CardModelResponse;
import utn.tacs.cards.application.find.CardFindRequest;
import utn.tacs.cards.application.find.CardFinder;
import utn.tacs.cards.domain.CardId;

@RequestMapping("api/cards")
@Api(tags = "Cards")
@CrossOrigin("*")
@RestController
public class CardsGetController {

    private CardFinder finder;

    public CardsGetController(CardFinder finder){
        this.finder = finder;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener una carta por ID")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "Carta correspondiente a ese id")
    })
    public CardModelResponse getCardById(@PathVariable("id") String id) {
        return finder.find(new CardFindRequest(new CardId(id)));
    }
}