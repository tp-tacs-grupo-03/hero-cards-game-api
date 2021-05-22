package utn.tacs.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import utn.tacs.dto.card.CardModelResponse;
import utn.tacs.dto.card.CardFindRequest;
import utn.tacs.services.CardService;
import utn.tacs.domain.CardId;

@Slf4j
@RequestMapping("api/cards")
@Api(tags = "Cards")
@CrossOrigin("*")
@RestController
public class CardController {

    private final CardService finder;

    public CardController(CardService finder){
        this.finder = finder;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener una carta por ID")
    @ApiResponses({
            @ApiResponse(code = 200, response = CardModelResponse.class, message = "Carta correspondiente a ese id")
    })
    public CardModelResponse getCard(@PathVariable("id") String id) {
        return finder.find(new CardFindRequest(new CardId(id)));
    }

}
