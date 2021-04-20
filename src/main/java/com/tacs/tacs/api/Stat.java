package com.tacs.tacs.api;
import java.sql.Date;
import java.util.UUID;

import com.tacs.tacs.model.responseModel.BattleModel;
import com.tacs.tacs.model.responseModel.MatchModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

@RequestMapping("api/stats")
@RestController
public class Stat {

    @GetMapping("/matches?status={matchStatus}&init_date={initDate}&finish_date={finish_date}")
    @ApiOperation(value = "Obtener cantidad de partidas de determinado estado entre determinadas fechas")
    public String getMatches(@PathVariable("matchStatus") String matchStatus, @PathVariable("initDate") String initDate,@PathVariable("finishDate") String finishDate ){
        return "Cantidad de partidas " + matchStatus;
    }

    @GetMapping("/leadderboard")
    @ApiOperation(value = "Obtener listado de estadisticas de los mejores jugadores")
    public String getLeadderboard(@PathVariable("userId") String userId){
        return "Historial del usuario numero "+ userId;
    }

    @GetMapping("/users/{userId}")
    @ApiOperation(value = "Obtener estadisticas personales de un usuario")
    public String getRecord(@PathVariable("userId") String userId){
        return "Historial del usuario numero "+ userId;
    }

}