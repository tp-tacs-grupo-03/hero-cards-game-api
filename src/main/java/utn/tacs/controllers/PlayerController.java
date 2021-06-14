package utn.tacs.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import utn.tacs.dto.deck.ListDeckModelResponse;
import utn.tacs.dto.deck.ListPlayerModelResponse;
import utn.tacs.pagination.Page;
import utn.tacs.pagination.exceptions.PaginationException;
import utn.tacs.services.PlayerService;
import utn.tacs.services.exceptions.CannotGetPlayers;
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
    @ApiOperation(value = "Get players")
    @ApiResponses({
            @ApiResponse(code = 200, response = ListDeckModelResponse.class, message = "Player lists")
    })
    public ListPlayerModelResponse getAllPlayers(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                 @RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortField,
                                                 @RequestParam(value = "name", required = false, defaultValue = "") String filterName,
                                                 @RequestParam(value = "sortDirection", required = false, defaultValue = "asc") String sortDirection) {
        try {
            return playerService.findAll(page, new Sort(sortField, sortDirection), filterName);
        } catch (SortingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (CannotGetPlayers pe) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, pe.getMessage(), pe);
        }
    }

}
