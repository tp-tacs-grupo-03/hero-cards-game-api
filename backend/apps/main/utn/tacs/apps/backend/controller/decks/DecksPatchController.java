package utn.tacs.apps.backend.controller.decks;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utn.tacs.cards.domain.Card;
import utn.tacs.decks.application.addCard.DeckAddRequest;
import utn.tacs.decks.application.addCard.DecksCardAdder;
import utn.tacs.decks.application.modify.DeckModifyRequest;
import utn.tacs.decks.application.modify.DecksModify;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("api/decks")
@Api(tags = "Decks")
@CrossOrigin("*")
@RestController
public class DecksPatchController {

    DecksModify decksModify;
    DecksCardAdder decksCardAdder;

    public DecksPatchController(DecksModify decksModify, DecksCardAdder decksCardAdder) {
        this.decksModify = decksModify;
        this.decksCardAdder = decksCardAdder;
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "Modificar un deck")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "Deck con la modificacion")
    })
    public void modifyDeck(@Validated @NonNull @RequestBody String name, @PathVariable("id") String id) throws Exception {
        decksModify.modify(new DeckModifyRequest(id, name));
    }

    @PatchMapping("/{id}/cards")
    @ApiOperation(value = "Agregar cartas al deck del id")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "Deck con la carta nueva")
    })
    public void addCard(@PathVariable("id") String id, @Validated @NonNull @RequestBody List<String> cards) throws Exception {
        decksCardAdder.add(new DeckAddRequest(id, cards.stream().map(Card::new).collect(Collectors.toList())));
    }

}
