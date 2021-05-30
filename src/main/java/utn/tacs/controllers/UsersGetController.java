package utn.tacs.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utn.tacs.dto.deck.response.ListUserModelResponse;
import utn.tacs.dto.deck.response.UserModelResponse;

@RequestMapping("api/users")
@Api(tags = "Users")
@RestController
public class UsersGetController {
    @GetMapping
    @ApiOperation(value = "Obtener todos los usuarios")
    @ApiResponses({
            @ApiResponse(code = 200, response = UserModelResponse.class, responseContainer = "List", message = "Listado de los jugadores")
    })
    public ListUserModelResponse getAllPlayers(@RequestParam(value = "offSet", required = false, defaultValue = "0") int offSet,
                                               @RequestParam(value = "limit", required = false, defaultValue = "100") int limit,
                                               @RequestParam(value = "sortBy", required = false) String sortField,
                                               @RequestParam(value = "sortDirection", required = false, defaultValue = "asc") String sortDirection) {
        return null;
    }
}
