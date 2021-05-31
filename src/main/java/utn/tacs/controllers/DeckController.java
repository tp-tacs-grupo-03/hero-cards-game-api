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
import org.springframework.web.server.ResponseStatusException;
import utn.tacs.controllers.exceptions.SomePowerStatsWithoutValueException;
import utn.tacs.domain.CardId;
import utn.tacs.dto.deck.*;
import utn.tacs.services.DeckService;

import java.util.HashMap;
import java.util.stream.Collectors;

@RequestMapping("api/decks")
@Api(tags = "Decks")
@RestController
public class DeckController {

    private DeckService deckService;
    private CardAttributesValidator cardValidator;

    public DeckController(DeckService deckService, CardAttributesValidator cardValidator) {
        this.deckService = deckService;
        this.cardValidator = cardValidator;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Borrar un deck por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "El deck se elimino")
    })
    public void deleteDeck(@PathVariable("id") String id){
        deckService.delete(new DeckDeleteRequest(id));
    }

    @GetMapping
    @ApiOperation(value = "Obtener todos los decks")
    @ApiResponses({
            @ApiResponse(code = 200, response = ListDeckModelResponse.class, message = "Listado de los decks")
    })
    public ListDeckModelResponse getAllDecks(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                               @RequestParam(value = "sortBy", required = false) String sortField,
                                               @RequestParam(value = "sortDirection", required = false, defaultValue = "asc") String sortDirection) {
        return deckService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener un deck por ID")
    @ApiResponses({
            @ApiResponse(code = 200, response = DeckModelResponse.class, message = "Deck asociado a ese id")
    })
    public DeckModelResponse getDeck(@PathVariable("id") String id) throws Exception {
        return deckService.findById(id);
    }

    @PostMapping
    @ApiOperation(value = "Crear deck")
    @ApiResponses({
            @ApiResponse(code = 200, response = DeckModelResponse.class, message = "Deck creado")
    })
    public DeckModelResponse newDeck(@Validated @NonNull @RequestBody DeckModelRequest deck){
        try {
            cardValidator.validate(deck.getCards());
        } catch (SomePowerStatsWithoutValueException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
        return deckService.create(
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

    @PutMapping("/{id}")
    @ApiOperation(value = "Modify the deck")
    @ApiResponses({@ApiResponse(code = 202, message = "Deck modified")})
    public ResponseEntity modifyDeck(@PathVariable("id") String id, @Validated @NonNull @RequestBody DeckModelRequest deckModelRequest) throws Exception {
        deckService.update(new DeckUpdateRequest(id, deckModelRequest.getName(), deckModelRequest.getCards()));
        return ResponseEntity.accepted().build();
    }
}
