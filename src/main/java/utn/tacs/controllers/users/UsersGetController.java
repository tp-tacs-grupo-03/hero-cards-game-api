package utn.tacs.controllers.users;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utn.tacs.dto.deck.response.UserModelRespones;

import java.util.List;

@RequestMapping("api/users")
@Api(tags = "Users")
@RestController
public class UsersGetController {
    @GetMapping
    @ApiOperation(value = "Obtener todos los usuarios")
    @ApiResponses({
            @ApiResponse(code = 200, response = UserModelRespones.class, responseContainer = "List", message = "Listado de los jugadores")
    })
    public List<UserModelRespones> getAllPlayers(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                                 @RequestParam(value = "sortBy", required = false) String sortField,
                                                 @RequestParam(value = "sortDirection", required = false, defaultValue = "asc") String sortDirection) {
        return null;
    }
}
