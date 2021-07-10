package utn.tacs.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import utn.tacs.dto.card.CardModelResponse;
import utn.tacs.dto.card.CardFindRequest;
import utn.tacs.services.CardService;
import utn.tacs.domain.CardId;

import java.net.URISyntaxException;

@Slf4j
@RequestMapping("api/cards")
@Api(tags = "Cards")
@RestController
@AllArgsConstructor
@CrossOrigin(value = "*", exposedHeaders = {"ETag"})
public class CardController {

    private final CardService finder;

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener una carta por ID")
    @ApiResponses({
            @ApiResponse(code = 200, response = CardModelResponse.class, message = "Carta correspondiente a ese id")
    })
    public CardModelResponse getCard(@PathVariable("id") String id) throws URISyntaxException {
        return finder.find(new CardFindRequest(new CardId(id)));
    }

}
