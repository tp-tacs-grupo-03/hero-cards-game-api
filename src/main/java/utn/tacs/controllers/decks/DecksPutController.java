package utn.tacs.controllers.decks;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utn.tacs.dto.deck.DeckModelRequest;
import utn.tacs.dto.deck.DeckUpdateRequest;
import utn.tacs.services.DecksUpdater;

@RequestMapping("api/decks")
@Api(tags = "Decks")
@CrossOrigin("*")
@RestController
public class DecksPutController {

    DecksUpdater updater;

    public DecksPutController(DecksUpdater updater) {
        this.updater = updater;
    }

    @PutMapping("/{id}/cards")
    @ApiOperation(value = "Modify the deck (name, cards) ")
    @ApiResponses({@ApiResponse(code = 202, message = "Deck modified")})
    public ResponseEntity modifyDeck(@PathVariable("id") String id, @Validated @NonNull @RequestBody DeckModelRequest deckModelRequest) throws Exception {
        updater.update(new DeckUpdateRequest(id, deckModelRequest.getName(), deckModelRequest.getCards()));
        return ResponseEntity.accepted().build();
    }
}
