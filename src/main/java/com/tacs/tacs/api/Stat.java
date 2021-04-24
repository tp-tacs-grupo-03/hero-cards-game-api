package com.tacs.tacs.api;

import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping("api/stats")
@RestController
public class Stat {

    @GetMapping("/matches")
    @ApiOperation(value = "Obtener cantidad de partidas de determinado estado entre determinadas fechas")
    @ApiResponses({
        @ApiResponse(code = 200, response = Object.class, message = "Estadisticas de las partidas en el estado dado entre las fechas informadas")
    })
    public String getMatches(@RequestParam(value = "matchStatus",required = false) String matchStatus, @RequestParam(value = "initDate", required = false) String initDate,@RequestParam(value = "finishDate",required = false) String finishDate ){
        return "Hay 12 partidas con el estado de " + matchStatus;
    }

    @GetMapping("/leadderboard")
    @ApiOperation(value = "Obtener listado de estadisticas de los mejores jugadores")
    @ApiResponses({
        @ApiResponse(code = 200, response = Object.class, message = "Tablero de posiciones")
    })
    public String getLeadderboard(){
        return "LEADBOARD actual ";
    }

    @GetMapping("/users/{userId}")
    @ApiOperation(value = "Obtener estadisticas personales de un usuario")
    @ApiResponses({
        @ApiResponse(code = 200, response = Object.class, message = "Estadisticas de un usuario")
    })
    public String getRecord(@PathVariable("userId") String userId){
        return "Estadisticas del usuario numero "+ userId;
    }

}