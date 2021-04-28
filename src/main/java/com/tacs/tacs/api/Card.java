package com.tacs.tacs.api;
import com.tacs.tacs.model.requestModel.*;
import com.tacs.tacs.model.responseModel.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/cards")
@RestController
public class Card {
    @GetMapping
    @ApiOperation(value = "Obtener todas las cartas")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "Listado de las cartas")
    })
    public List<CardDataModel> getAllCards(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                                 @RequestParam(value = "sortBy", required = false) String sortField,
                                                 @RequestParam(value = "sortDirection", required = false, defaultValue = "asc") String sortDirection) {
        return null;
    }
}