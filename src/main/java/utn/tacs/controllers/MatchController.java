package utn.tacs.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import utn.tacs.common.security.Authenticator;
import utn.tacs.dto.deck.response.MatchModel;
import utn.tacs.dto.match.*;
import utn.tacs.services.MatchService;
import utn.tacs.sorting.exceptions.SortingException;

import java.util.ArrayList;
import java.util.List;



@RequestMapping("api/matches")
@Api(tags = "Matches")
@RestController
@AllArgsConstructor
@CrossOrigin(value = "*", exposedHeaders = {"ETag"})
public class MatchController {

    private final MatchService matchService;
    private final Authenticator authentication;

    @GetMapping
    @ApiOperation(value = "Obtener todas las partidas")
    @ApiResponses({
            @ApiResponse(code = 200, response = ListMatchModelResponse.class, message = "Las partidas")
    })
    @PreAuthorize(value = "hasAuthority('read:matches')")
    public ListMatchModelResponse getAllMatches(@RequestParam(value = "size"            ,required = false, defaultValue = "100" ) int size,
                                                @RequestParam(value = "page"            ,required = false, defaultValue = "0"   ) int page,
                                                @RequestParam(value = "sortBy"          ,required = false, defaultValue = "id"  ) String sortField ,
                                                @RequestParam(value = "sortDirection"   ,required = false, defaultValue = "asc" ) String sortDirection,
                                                @RequestParam(value = "battles"         ,required = false, defaultValue = "true") boolean battles)
    {
        try {
            return matchService.findAll(new MatchPagingRequest(sortField, page, size, sortDirection, battles));
        } catch (SortingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener una partida por id")
    @ApiResponses({
            @ApiResponse(code = 200, response = MatchModelResponse.class, message = "La partida")
    })
    @PreAuthorize(value = "hasAuthority('read:matches')")
    public MatchModelResponse getMatch(@PathVariable("id") String id,
                                       @RequestParam(value = "battles", required = false, defaultValue = "true") boolean battle)
    {
        try {
            return matchService.find(new MatchFindRequest(id, battle, authentication.getHost()));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    @ApiOperation(value = "Crear partida, se divide y mezcla el mazo elegido")
    @ApiResponses({
            @ApiResponse(code = 200, response = MatchModelResponse.class, message = "match")
    })
    @PreAuthorize(value = "hasAuthority('create:matches')")
    public MatchModelResponse postMatch(@Validated @NonNull @RequestBody MatchModel matchRequest) throws Exception {
        final String hostId = authentication.getHost();
        return matchService.create(new MatchCreateRequest(matchRequest.getOpponentId(), matchRequest.getDeckId(), hostId, matchRequest.getType()));
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "Actualizar partida")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Surrender")
    })
    @PreAuthorize(value = "hasAuthority('update:matches')")
    public MatchModelResponse update(@RequestBody MatchRequest request, @PathVariable("id") String id ) throws Exception {
        final MatchUpdateRequest matchUpdateRequest = new MatchUpdateRequest();

        if (request.getAttribute() != null && request.getStatus() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        matchUpdateRequest.setStatus(request.getStatus());
        matchUpdateRequest.setId(id);
        matchUpdateRequest.setPlayer(authentication.getHost());
        matchUpdateRequest.setAttribute(request.getAttribute());

        return matchService.update(matchUpdateRequest);
    }

}
