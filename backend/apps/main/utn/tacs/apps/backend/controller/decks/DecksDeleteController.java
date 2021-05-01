package utn.tacs.apps.backend.controller.decks;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import utn.tacs.cards.domain.Card;
import utn.tacs.decks.application.delete.DeckDeleteRequest;
import utn.tacs.decks.application.delete.DecksDeleter;
import utn.tacs.decks.application.deleteCard.DeckCardDeleteRequest;
import utn.tacs.decks.application.deleteCard.DecksCardDeleter;

@RequestMapping("api/decks")
@Api(tags = "Decks")
@RestController
public class DecksDeleteController {

    DecksCardDeleter decksCardDeleter;
    DecksDeleter decksDeleter;

    public DecksDeleteController(DecksCardDeleter decksCardDeleter, DecksDeleter decksDeleter) {
        this.decksCardDeleter = decksCardDeleter;
        this.decksDeleter = decksDeleter;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Borrar un deck por ID")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "Deck eliminado")
    })
    public void deleteDeck(@PathVariable("id") String id){
        decksDeleter.delete(new DeckDeleteRequest(id));
    }

    @DeleteMapping("/{id}/cards")
    @ApiOperation(value = "Quita una carta al deck del id")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "Deck con la carta nueva")
    })
    public void deleteCard(@PathVariable("id") String id, @RequestBody Card card) throws Exception {
        decksCardDeleter.delete(new DeckCardDeleteRequest(id, card));
    }
}
