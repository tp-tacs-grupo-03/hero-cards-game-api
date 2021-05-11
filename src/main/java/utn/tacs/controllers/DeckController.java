package utn.tacs.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utn.tacs.domain.CardId;
import utn.tacs.dto.deck.*;
import utn.tacs.services.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("api/decks")
@Api(tags = "Decks")
@CrossOrigin("*")
@RestController
public class DeckController {

    private DecksCleaner decksDeleter;
    private DecksFinder decksFinder;
    private DecksModify decksModify;
    private DecksCreator creator;
    private DecksUpdater updater;

    public DeckController(DecksCleaner decksDeleter, DecksFinder decksFinder, DecksModify decksModify, DecksCreator creator, DecksUpdater updater) {
        this.decksFinder = decksFinder;
        this.decksDeleter = decksDeleter;
        this.decksModify = decksModify;
        this.creator = creator;
        this.updater = updater;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Borrar un deck por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Deck eliminado")
    })
    public void deleteDeck(@PathVariable("id") String id){
        decksDeleter.delete(new DeckDeleteRequest(id));
    }

    @GetMapping
    @ApiOperation(value = "Obtener todos los decks")
    @ApiResponses({
            @ApiResponse(code = 200, response = DeckModelResponse.class, responseContainer ="List", message = "Listado de los decks")
    })
    public List<DeckModelResponse> getAllDecks(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                               @RequestParam(value = "sortBy", required = false) String sortField,
                                               @RequestParam(value = "sortDirection", required = false, defaultValue = "asc") String sortDirection) {
        return decksFinder.findAll();
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener un deck por ID")
    @ApiResponses({
            @ApiResponse(code = 200, response = DeckModelResponse.class, message = "Deck asociado a ese id")
    })
    public DeckModelResponse getDeck(@PathVariable("id") String id) throws Exception {
        return decksFinder.findById(id);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "Modificar un deck")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Deck con la modificacion")
    })
    public void modifyDeck(@Validated @NonNull @RequestBody String name, @PathVariable("id") String id) throws Exception {
        decksModify.modify(new DeckModifyRequest(id, name));
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

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<?> handleInvalidDNAException(Exception e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("status", HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}/cards")
    @ApiOperation(value = "Modify the deck (name, cards) ")
    @ApiResponses({@ApiResponse(code = 202, message = "Deck modified")})
    public ResponseEntity modifyDeck(@PathVariable("id") String id, @Validated @NonNull @RequestBody DeckModelRequest deckModelRequest) throws Exception {
        updater.update(new DeckUpdateRequest(id, deckModelRequest.getName(), deckModelRequest.getCards()));
        return ResponseEntity.accepted().build();
    }
}
