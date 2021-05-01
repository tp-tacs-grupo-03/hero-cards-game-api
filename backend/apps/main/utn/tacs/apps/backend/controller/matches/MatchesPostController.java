package utn.tacs.apps.backend.controller.matches;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import utn.tacs.model.responseModel.MatchModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@RequestMapping("api/matches")
@Api(tags = "Matches")
@RestController
public class MatchesPostController {
    @PostMapping
    @ApiOperation(value = "Crear partida, se divide y mezcla el mazo elegido")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "match")
    })
    public MatchModel postMatch(@Validated @NonNull @RequestBody MatchModel match){
        match.setId(UUID.randomUUID().toString());
        return match;
    }
}
