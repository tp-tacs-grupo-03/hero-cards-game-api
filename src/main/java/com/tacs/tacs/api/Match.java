package com.tacs.tacs.api;
import java.util.*;

import com.tacs.tacs.model.responseModel.CardDataModel;
import com.tacs.tacs.model.responseModel.MatchModel;

import com.tacs.tacs.model.responseModel.PlayerStatusEnum;
import io.swagger.annotations.Authorization;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping("api/matches")
@RestController
public class Match {
    
    @GetMapping
    @ApiOperation(value = "Obtener todas las partidas")
    @ApiResponses({
        @ApiResponse(code = 200, response = Object.class, message = "Las partidas")
    })
    public List<MatchModel> getAllMatches(@RequestParam(value = "page",required = false, defaultValue = "0") int page,
                                          @RequestParam(value = "pageSize",required = false, defaultValue = "10") int pageSize,
                                          @RequestParam(value = "sortBy",required = false) String sortField,
                                          @RequestParam(value = "sortDirection",required = false, defaultValue = "asc") String sortDirection){
        return null;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener una partida por id")
    @ApiResponses({
        @ApiResponse(code = 200, response = Object.class, message = "La partida")
    })
    public MatchModel getMatch(@PathVariable("id") int id){
        return null;
    }
   
    @PostMapping
    @ApiOperation(value = "Crear partida, se divide y mezcla el mazo elegido")
    @ApiResponses({
        @ApiResponse(code = 200, response = Object.class, message = "match")
    })
    public MatchModel postMatch(@Validated @NonNull @RequestBody MatchModel match){
        match.setId(UUID.randomUUID().toString());
        return match;
    }

    @GetMapping("/{id}/cards")
    @ApiOperation(value = "Obtener carta actual del mazo")
    @ApiResponses({
        @ApiResponse(code = 200, response = Object.class, message = "La carta")
    })
    public CardDataModel draw(@PathVariable("id") int id){
        return null;
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "Modificar un match")
    @ApiResponses({
        @ApiResponse(code = 200, response = Object.class, message = "Match con la modificación")
    })
    public Object modifyMatch(@RequestParam(value = "playerStatus") String playerStatus, @PathVariable("id") int id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
    @GetMapping("/{id}/turns")
    @ApiOperation(value = "Recrear una partida")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "La partida recreada")
    })
    public MatchModel recreateMatch(@PathVariable("id") int id){
        return null;
    }
}