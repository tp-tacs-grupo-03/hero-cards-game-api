package com.tacs.tacs.api;
import java.util.UUID;

import com.tacs.tacs.model.responseModel.BattleModel;
import com.tacs.tacs.model.responseModel.MatchModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

@RequestMapping("api/matches")
@RestController
public class Match {

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener una partida por id")
    public String getMatch(@PathVariable("id") int id){
        return "esta es la partida numero " + id;
    }
   
    @PostMapping
    @ApiOperation(value = "Crear partida, se divide y mezcla el mazo elegido")
    public MatchModel postMatch(@Validated @NonNull @RequestBody MatchModel match){
        match.id = UUID.randomUUID().toString();
        return match;
    }

    @GetMapping("/{id}/draw")
    @ApiOperation(value = "Obtener proxima carta del mazo")
    public String draw(@PathVariable("id") int id){
        return "agarro una carta";
    }
    @PostMapping("/{id}/ready")
    @ApiOperation(value = "Actualiza estado del jugador a READY, en caso de ser su turno debe especificar un atributo elegido")
    public String ready(@Validated @NonNull @RequestBody BattleModel battle){
        return "se pelea con el atributo "+ battle.attribute;
    }
    @PostMapping("/{id}/surrender")
    @ApiOperation(value = "Abandona una partida segun id")
    public String surrender(){
        return "El usuario abandona la partida";
    }

}