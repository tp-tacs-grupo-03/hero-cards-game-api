package utn.tacs.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utn.tacs.dto.card.CardModelResponse;
import utn.tacs.dto.card.CardFindRequest;
import utn.tacs.dto.deck.DeckAddRequest;
import utn.tacs.dto.deck.DeckCardDeleteRequest;
import utn.tacs.services.CardFinder;
import utn.tacs.domain.CardId;
import utn.tacs.services.DecksCardCleaner;
import utn.tacs.services.DecksCardCreator;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("api/cards")
@Api(tags = "Cards")
@CrossOrigin("*")
@RestController
public class CardController {

    private CardFinder finder;
    private DecksCardCleaner decksCardCleaner;
    private DecksCardCreator decksCardCreator;

    public CardController(CardFinder finder, DecksCardCleaner decksCardCleaner, DecksCardCreator decksCardCreator){
        this.decksCardCleaner = decksCardCleaner;
        this.finder = finder;
        this.decksCardCreator = decksCardCreator;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener una carta por ID")
    @ApiResponses({
            @ApiResponse(code = 200, response = CardModelResponse.class, message = "Carta correspondiente a ese id")
    })
    public CardModelResponse getCard(@PathVariable("id") String id) {
        return finder.find(new CardFindRequest(new CardId(id)));
    }

    @DeleteMapping("/{id}/cards")
    @ApiOperation(value = "Quita una carta al deck del id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Deck con la carta nueva")
    })
    public void deleteCard(@PathVariable("id") String id, @RequestBody CardId cardId) throws Exception {
        decksCardCleaner.delete(new DeckCardDeleteRequest(id, cardId));
    }

    @PatchMapping("/{id}/cards")
    @ApiOperation(value = "Agregar cartas al deck del id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Deck con la carta nueva")
    })
    public void addCard(@PathVariable("id") String id, @Validated @NonNull @RequestBody List<String> cardIds) throws Exception {
        decksCardCreator.add(new DeckAddRequest(id, cardIds.stream().map(CardId::new).collect(Collectors.toList())));
    }
}
