package utn.tacs.apps.backend.controller.decks;

import utn.tacs.model.requestModel.DeckModelRequest;
import utn.tacs.model.responseModel.DeckModelResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/decks")
@RestController
public class DecksPatchController {

    @PatchMapping("/{id}")
    @ApiOperation(value = "Modificar un deck")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "Deck con la modificacion")
    })
    public DeckModelResponse modifyDeck(@Validated @NonNull @RequestBody DeckModelRequest deck){
        return null;
    }
}
