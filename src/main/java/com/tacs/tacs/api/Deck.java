package com.tacs.tacs.api;


import com.tacs.tacs.model.requestModel.*;
import com.tacs.tacs.model.responseModel.*;

import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@RequestMapping("api/decks")
@RestController
public class Deck {
    @GetMapping
    @ApiOperation(value = "Obtener todos los decks")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "Listado de los decks")
    })
    public List<DeckModelResponse> getAllDecks(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                                 @RequestParam(value = "sortBy", required = false) String sortField,
                                                 @RequestParam(value = "sortDirection", required = false, defaultValue = "asc") String sortDirection) {
        return null;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener un deck por ID")
    @ApiResponses({
        @ApiResponse(code = 200, response = Object.class, message = "Deck asociado a ese id")
    })
    public DeckModelResponse getDeck(@PathVariable("id") int id){
        return new DeckModelResponse(null,id,"deck1");
    }

    @PostMapping("/{id}/cards")
    @ApiOperation(value = "Agregar una carta al deck de id")
    @ApiResponses({
        @ApiResponse(code = 200, response = Object.class, message = "Deck con la carta nueva")
    })
    public DeckModelResponse addCard(@PathVariable("id") int id, @Validated @NonNull @RequestBody CardModel card){
        return null;
    }

    @DeleteMapping("/{id}/cards/{card_id}")
    @ApiOperation(value = "Borrar una carta id al deck de id")
    @ApiResponses({
        @ApiResponse(code = 200, response = Object.class, message = "Deck luego de la eliminacion de una carta")
    })
    public DeckModelResponse deleteCard(@PathVariable("id") int id, @PathVariable("card_id") int card_id){
        return null;
    }
   
    @PostMapping
    @ApiOperation(value = "Crear deck")
    @ApiResponses({
        @ApiResponse(code = 200, response = Integer.class, message = "Deck creado")
    })
    public DeckModelResponse newDeck(@Validated @NonNull @RequestBody DeckModelRequest deck){
        return null;
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "Modificar un deck")
    @ApiResponses({
        @ApiResponse(code = 200, response = Object.class, message = "Deck con la modificacion")
    })
    public DeckModelResponse modifyDeck(@Validated @NonNull @RequestBody DeckModelRequest deck){
        return null;
    }
    
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Borrar un deck por ID")
    @ApiResponses({
            @ApiResponse(code = 200, response = Object.class, message = "Deck eliminado")
    })
    public DeckModelResponse deleteDeck(@PathVariable("id") int id){
        return null;
    }

}