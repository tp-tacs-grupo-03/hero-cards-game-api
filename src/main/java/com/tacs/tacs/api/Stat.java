package com.tacs.tacs.api;
import java.sql.Date;
import java.util.UUID;

import com.tacs.tacs.model.responseModel.BattleModel;
import com.tacs.tacs.model.responseModel.MatchModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/stats")
@RestController
public class Stat {

    @GetMapping("/matches?status={matchStatus}&init_date={initDate}&finish_date={finish_date}")
    public String getMatches(@PathVariable("matchStatus") String matchStatus, @PathVariable("initDate") Date initDate,@PathVariable("finishDate") Date finishDate ){
        return "Cantidad de partidas " + matchStatus;
    }

    @GetMapping("/users/{userId}")
    public String GetRecord(@PathVariable("userId") String userId){
        return "Historial del usuario numero "+ userId;
    }

}