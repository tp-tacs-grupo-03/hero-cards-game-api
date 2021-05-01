package utn.tacs.apps.backend.controller.decks;

import io.swagger.annotations.Api;
import utn.tacs.model.responseModel.DeckModelResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/decks")
@Api(tags = "Decks")
@RestController
public class DecksDeleteController {

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Borrar un deck por ID")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "Deck eliminado")
    })
    public DeckModelResponse deleteDeck(@PathVariable("id") int id){
        return null;
    }
}
