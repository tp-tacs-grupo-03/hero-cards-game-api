package utn.tacs.apps.backend.controller.decks;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utn.tacs.cards.domain.Card;
import utn.tacs.decks.application.create.DeckCreateRequest;
import utn.tacs.decks.application.create.DecksCreator;
import utn.tacs.model.requestModel.DeckModelRequest;
import utn.tacs.model.responseModel.DeckModelResponse;

import java.util.stream.Collectors;

@RequestMapping("api/decks")
@Api(tags = "Decks")
@CrossOrigin("*")
@RestController
public class DecksPostController {

    DecksCreator creator;

    public DecksPostController(DecksCreator creator) {
        this.creator = creator;
    }

    @PostMapping
    @ApiOperation(value = "Crear deck")
    @ApiResponses({
            @ApiResponse(code = 200, response = Integer.class, message = "Deck creado")
    })
    public DeckModelResponse newDeck(@Validated @NonNull @RequestBody DeckModelRequest deck){
        return creator.create(
                new DeckCreateRequest(
                        deck.getCards().stream().map(Card::new).collect(Collectors.toList()),
                        deck.getNombre()
                )
        );
    }
}
