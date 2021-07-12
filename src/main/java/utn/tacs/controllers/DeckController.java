package utn.tacs.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import utn.tacs.controllers.exceptions.SomePowerStatsWithoutValueException;
import utn.tacs.controllers.validators.CardAttributesValidator;
import utn.tacs.domain.CardId;
import utn.tacs.dto.deck.*;
import utn.tacs.services.DeckService;
import utn.tacs.sorting.Sort;
import utn.tacs.sorting.exceptions.SortingException;

import java.util.HashMap;
import java.util.stream.Collectors;

@RequestMapping("api/decks")
@Api(tags = "Decks")
@RestController
@CrossOrigin(value = "*", exposedHeaders = {"ETag"})
@AllArgsConstructor
public class DeckController {

    private DeckService deckService;
    private CardAttributesValidator cardValidator;

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Borrar un deck por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "El deck se elimino")
    })
    @PreAuthorize(value = "hasAuthority('delete:decks')")
    public void deleteDeck(@PathVariable("id") String id){
        deckService.delete(new DeckDeleteRequest(id));
    }

    @GetMapping
    @ApiOperation(value = "Obtener todos los decks")
    @ApiResponses({
            @ApiResponse(code = 200, response = ListDeckModelResponse.class, message = "Listado de los decks")
    })
    @PreAuthorize("hasAuthority('read:decks')")
    public ListDeckModelResponse getAllDecks(@RequestParam(value = "size", required = false, defaultValue = "100") int size,
                                               @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                               @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortField,
                                               @RequestParam(value = "sortDirection", required = false, defaultValue = "asc") String sortDirection) {
        try {
            final Pageable pageable = PageRequest.of(page, size);
            return deckService.findAll(pageable, new Sort(sortField, sortDirection));
        } catch (SortingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener un deck por ID")
    @ApiResponses({
            @ApiResponse(code = 200, response = DeckModelResponse.class, message = "Deck asociado a ese id")
    })
    @PreAuthorize(value = "hasAuthority('read:decks')")
    public DeckModelResponse getDeck(@PathVariable("id") String id) throws Exception {
        return deckService.findById(id);
    }

    @PostMapping
    @ApiOperation(value = "Crear deck")
    @ApiResponses({
            @ApiResponse(code = 200, response = DeckModelResponse.class, message = "Deck creado")
    })
    @PreAuthorize(value = "hasAuthority('create:decks')")
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


    @PutMapping("/{id}")
    @ApiOperation(value = "Modify the deck")
    @ApiResponses({@ApiResponse(code = 202, message = "Deck modified")})
    @PreAuthorize(value = "hasAuthority('update:decks')")
    public ResponseEntity modifyDeck(@PathVariable("id") String id, @Validated @NonNull @RequestBody DeckModelRequest deckModelRequest) throws Exception {
        deckService.update(new DeckUpdateRequest(id, deckModelRequest.getName(), deckModelRequest.getCards()));
        return ResponseEntity.accepted().build();
    }
}
