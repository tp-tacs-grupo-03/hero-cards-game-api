package utn.tacs.controllers.decks;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import utn.tacs.domain.CardId;
import utn.tacs.dto.deck.DeckCardDeleteRequest;
import utn.tacs.dto.deck.DeckDeleteRequest;
import utn.tacs.services.DecksCardDeleter;
import utn.tacs.services.DecksDeleter;

@RequestMapping("api/decks")
@Api(tags = "Decks")
@CrossOrigin("*")
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
    public void deleteCard(@PathVariable("id") String id, @RequestBody CardId cardId) throws Exception {
        decksCardDeleter.delete(new DeckCardDeleteRequest(id, cardId));
    }
}