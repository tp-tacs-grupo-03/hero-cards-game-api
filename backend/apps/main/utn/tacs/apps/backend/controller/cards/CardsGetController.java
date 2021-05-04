package utn.tacs.apps.backend.controller.cards;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.tacs.cards.application.list.CardsLister;
import utn.tacs.superHeroAPI.clientApi.SuperHeroApi;
import utn.tacs.superHeroAPI.clientApi.model.Character;

@RequestMapping("api/cards")
@Api(tags = "Cards")
@CrossOrigin("*")
@RestController
public class CardsGetController {
    private CardsLister lister;

    public CardsGetController(CardsLister lister){
        this.lister = lister;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener una carta por ID")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "Carta correspondiente a ese id")
    })
    public Character getCardById(@PathVariable("id") String id) {
        final SuperHeroApi client = new SuperHeroApi();
        ResponseEntity<Character> response = client.getCharacter(id);
        return response.getBody();
    }
}