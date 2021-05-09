package utn.tacs.controllers.decks;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utn.tacs.domain.CardId;
import utn.tacs.dto.deck.DeckCreateRequest;
import utn.tacs.dto.deck.DeckModelRequest;
import utn.tacs.dto.deck.DeckModelResponse;
import utn.tacs.services.DecksCreator;

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
            @ApiResponse(code = 200, response = DeckModelResponse.class, message = "Deck creado")
    })
    public DeckModelResponse newDeck(@Validated @NonNull @RequestBody DeckModelRequest deck){
        return creator.create(
                new DeckCreateRequest(
                        deck.getCards().stream().map(CardId::new).collect(Collectors.toList()),
                        deck.getName()
                )
        );
    }
}
