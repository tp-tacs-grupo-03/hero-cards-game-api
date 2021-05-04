package utn.tacs.apps.backend.controller.decks;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utn.tacs.decks.application.update.DeckUpdateRequest;
import utn.tacs.decks.application.update.DecksUpdater;
import utn.tacs.model.requestModel.DeckModelRequest;

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
    @ApiResponses({@ApiResponse(code = 202, response = Object.class, message = "Deck modified")})
    public ResponseEntity modifyDeck(@PathVariable("id") String id, @Validated @NonNull @RequestBody DeckModelRequest deckModelRequest) throws Exception {
        updater.update(new DeckUpdateRequest(id, deckModelRequest.getName(), deckModelRequest.getCards()));
        return ResponseEntity.accepted().build();
    }
}
