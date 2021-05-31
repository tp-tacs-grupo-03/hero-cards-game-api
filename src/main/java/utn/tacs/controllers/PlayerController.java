package utn.tacs.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import utn.tacs.dto.deck.*;
import utn.tacs.pagination.Page;
import utn.tacs.pagination.exceptions.PaginationException;
import utn.tacs.services.PlayerService;
import utn.tacs.sorting.Sort;
import utn.tacs.sorting.exceptions.SortingException;

@RequestMapping("api/players")
@Api(tags = "Players")
@CrossOrigin("*")
@RestController
public class PlayerController {

    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    @ApiOperation(value = "Get all players")
    @ApiResponses({
            @ApiResponse(code = 200, response = ListDeckModelResponse.class, message = "Player lists")
    })
    public ListPlayerModelResponse getAllPlayers(@RequestParam(value = "limit", required = false, defaultValue = "100") int limit,
                                                 @RequestParam(value = "offSet", required = false, defaultValue = "0") int offSet,
                                                 @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortField,
                                                 @RequestParam(value = "sortDirection", required = false, defaultValue = "asc") String sortDirection) {
        try {
            return playerService.findAll(new Page(offSet, limit), new Sort(sortField, sortDirection));
        } catch (SortingException |PaginationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }


    @PostMapping
    @ApiOperation(value = "Add a new player")
    @ApiResponses({
            @ApiResponse(code = 200, response = PlayerModelResponse.class, message = "Player created.")
    })
    public PlayerModelResponse addPlayer(@Validated @NonNull @RequestBody PlayerModelRequest player){
        return playerService.create( new PlayerCreateRequest(player.getId(), player.getName())
        );
    }

}
