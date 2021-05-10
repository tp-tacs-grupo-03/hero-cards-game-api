package utn.tacs.controllers.matches;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import utn.tacs.dto.deck.response.Request;

@RequestMapping("api/matches")
@Api(tags = "Matches")
@CrossOrigin("*")
@RestController
public class MatchesPatchController {


    @PatchMapping("/{id}")
    @ApiOperation(value = "Surrendear partida")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Surrender")
    })
    public void surrender(@PathVariable("id") String id ) {
    }
}
