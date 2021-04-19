package com.tacs.tacs.api;
import java.util.UUID;

import com.tacs.tacs.model.responseModel.BattleModel;
import com.tacs.tacs.model.responseModel.MatchModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/matches")
@RestController
public class Match {

    @GetMapping("/{id}")
    public String getMatch(@PathVariable("id") int id){
        return "esta es la partida numero " + id;
    }
   
    @PostMapping
    public MatchModel postMatch(@Validated @NonNull @RequestBody MatchModel match){
        match.id = UUID.randomUUID().toString();
        return match;
    }

    @GetMapping("/{id}/draw")
    public String draw(@PathVariable("id") int id){
        return "agarro una carta";
    }
    @PostMapping("/{id}/ready")
    public String ready(@Validated @NonNull @RequestBody BattleModel battle){
        return "se pelea con el atributo "+ battle.attribute;
    }
    @PostMapping("/{id}/surrender")
    public String surrender(){
        return "El usuario abandona la partida";
    }

}