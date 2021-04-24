package com.tacs.tacs.api;
import java.util.UUID;

import com.tacs.tacs.model.responseModel.BattleModel;
import com.tacs.tacs.model.responseModel.MatchModel;

import org.springframework.lang.NonNull;
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
    public String getAllMatches(){
        return "todos los matches";
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener una partida por id")
    @ApiResponses({
        @ApiResponse(code = 200, response = Object.class, message = "La partida")
    })
    public String getMatch(@PathVariable("id") int id){
        return "esta es la partida numero " + id;
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

    @GetMapping("/{id}/draw")
    @ApiOperation(value = "Obtener proxima carta del mazo")
    @ApiResponses({
        @ApiResponse(code = 200, response = Object.class, message = "La carta")
    })
    public String draw(@PathVariable("id") int id){
        return "agarro una carta";
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "Modificar un deck")
    @ApiResponses({
        @ApiResponse(code = 200, response = Object.class, message = "Deck con la modificacion")
    })
    public MatchModel modifyMatch(@Validated @NonNull @RequestBody MatchModel match){
        return match;
    }
}