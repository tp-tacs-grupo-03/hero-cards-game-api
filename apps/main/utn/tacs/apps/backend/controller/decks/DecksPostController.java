package utn.tacs.apps.backend.controller.decks;

import utn.tacs.model.requestModel.CardModel;
import utn.tacs.model.requestModel.DeckModelRequest;
import utn.tacs.model.responseModel.DeckModelResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/decks")
@RestController
public class DecksPostController {

    @PostMapping("/{id}/cards")
    @ApiOperation(value = "Agregar una carta al deck de id")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "Deck con la carta nueva")
    })
    public DeckModelResponse addCard(@PathVariable("id") int id, @Validated @NonNull @RequestBody CardModel card){
        return null;
    }


    @PostMapping
    @ApiOperation(value = "Crear deck")
    @ApiResponses({
            @ApiResponse(code = 200, response = Integer.class, message = "Deck creado")
    })
    public DeckModelResponse newDeck(@Validated @NonNull @RequestBody DeckModelRequest deck){
        return null;
    }
}
