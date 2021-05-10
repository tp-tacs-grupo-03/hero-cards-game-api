package utn.tacs.controllers.decks;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import utn.tacs.domain.CardId;
import utn.tacs.dto.deck.DeckCardDeleteRequest;
import utn.tacs.dto.deck.DeckDeleteRequest;
import utn.tacs.services.DecksCardCleaner;
import utn.tacs.services.DecksCleaner;

@RequestMapping("api/decks")
@Api(tags = "Decks")
@CrossOrigin("*")
@RestController
public class DecksDeleteController {

    DecksCleaner decksDeleter;

    public DecksDeleteController(DecksCleaner decksDeleter) {
        this.decksDeleter = decksDeleter;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Borrar un deck por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "El deck se elimino")
    })
    public void deleteDeck(@PathVariable("id") String id){
        decksDeleter.delete(new DeckDeleteRequest(id));
    }

}
