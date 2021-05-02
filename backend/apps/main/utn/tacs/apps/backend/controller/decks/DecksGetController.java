package utn.tacs.apps.backend.controller.decks;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.tacs.decks.application.find.DecksFinder;
import utn.tacs.decks.application.list.DecksLister;
import utn.tacs.model.responseModel.DeckModelResponse;

import java.util.HashMap;
import java.util.List;

@RequestMapping("api/decks")
@Api(tags = "Decks")
@CrossOrigin("*")
@RestController
public class DecksGetController {

    DecksFinder decksFinder;
    DecksLister decksLister;

    public DecksGetController(DecksFinder decksFinder, DecksLister decksLister) {
        this.decksFinder = decksFinder;
        this.decksLister = decksLister;
    }

    @GetMapping
    @ApiOperation(value = "Obtener todos los decks")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "Listado de los decks")
    })
    public List<DeckModelResponse> getAllDecks(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                               @RequestParam(value = "sortBy", required = false) String sortField,
                                               @RequestParam(value = "sortDirection", required = false, defaultValue = "asc") String sortDirection) {
        return decksLister.list();
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener un deck por ID")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "Deck asociado a ese id")
    })
    public DeckModelResponse getDeck(@PathVariable("id") String id) throws Exception {
        return decksFinder.findById(id);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<?> handleInvalidDNAException(Exception e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("status", HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
