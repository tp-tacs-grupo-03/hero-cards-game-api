package utn.tacs.apps.backend.controller.matches;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import utn.tacs.model.responseModel.Request;


@RequestMapping("api/matches")
@Api(tags = "Matches")
@RestController
public class MatchesPatchController {



    @PatchMapping("/{id}")
    @ApiOperation(value = "Modificar un match")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "Match con la modificación")
    })
    public Object modifyMatch(@RequestBody Request request, @PathVariable("id") int id ){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return request;
    }

}
