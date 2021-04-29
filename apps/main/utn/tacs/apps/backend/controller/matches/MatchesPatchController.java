package utn.tacs.apps.backend.controller.matches;

import com.tacs.tacs.model.responseModel.MatchModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/matches")
@RestController
public class MatchesPatchController {

    @PatchMapping("/{id}")
    @ApiOperation(value = "Modificar un match")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "Match con la modificación")
    })
    public MatchModel modifyMatch(@RequestParam(value = "playerStatus") String playerStatus, @PathVariable("id") int id){
        return null;
    }
}
