package utn.tacs.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import utn.tacs.dto.player.ListPlayerModelResponse;
import utn.tacs.dto.player.User;
import utn.tacs.services.PlayerService;

@RequestMapping("api/players")
@Api(tags = "Players")
@CrossOrigin(value = "*", exposedHeaders = {"ETag"})
@AllArgsConstructor
@RestController
public class PlayerController {

    private final PlayerService playerService;


    @GetMapping
    @ApiOperation(value = "Get players")
    @ApiResponses({
            @ApiResponse(code = 200, response = ListPlayerModelResponse.class, message = "Player lists")
    })
    public ListPlayerModelResponse getAllPlayers(@RequestParam(value = "size",required = false, defaultValue = "100") int size,
                                                 @RequestParam(value = "page",required = false, defaultValue = "0") int page,
                                                 @RequestParam(value = "name", required = false, defaultValue = "") String filterName) {
        try {
            final Pageable pageable = PageRequest.of(page, size);
            return playerService.findAll(pageable, filterName);
        } catch (Exception pe) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, pe.getMessage(), pe);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get username by id")
    @ApiResponses({
            @ApiResponse(code = 200, response = User.class, message = "Username by id")
    })
    public User getUsernameByID(@PathVariable("id") String id) {
        try {
            return playerService.findUserById(id);
        }
        catch (Exception pe) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, pe.getMessage(), pe);
        }
    }
}
