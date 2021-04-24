package com.tacs.tacs.api;


import com.tacs.tacs.model.requestModel.*;
import com.tacs.tacs.model.responseModel.*;

import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping("api/decks")
@RestController
public class Deck {

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener un deck por ID")
    @ApiResponses({
        @ApiResponse(code = 200, response = Object.class, message = "Deck asociado a ese id")
    })
    public String getDeck(@PathVariable("id") int id){
        return "este es el deck numero " + id + "\n Sus cartas son: ";
    }

    @PostMapping("/{id}/cards")
    @ApiOperation(value = "Agregar una carta al deck de id")
    @ApiResponses({
        @ApiResponse(code = 200, response = Object.class, message = "Deck con la carta nueva")
    })
    public String addCard(@PathVariable("id") int id, @Validated @NonNull @RequestBody CardModel card){
        return "este es el deck numero " + id + "\n Sus cartas son: [" + card.getId() + "]";
    }

    @DeleteMapping("/{id}/cards/{card_id}")
    @ApiOperation(value = "Borrar una carta id al deck de id")
    @ApiResponses({
        @ApiResponse(code = 200, response = Object.class, message = "Deck luego de la eliminacion")
    })
    public String deleteCard(@PathVariable("id") int id, @PathVariable("card_id") int card_id){
        return "este es el deck numero " + id + "\n Sus cartas son: []";
    }
   
    @PostMapping
    @ApiOperation(value = "Crear deck")
    @ApiResponses({
        @ApiResponse(code = 200, response = Integer.class, message = "Id del deck creado")
    })
    public int newDeck(@Validated @NonNull @RequestBody DeckModelRequest deck){
        return 1;
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
    public void deleteDeck(@PathVariable("id") int id){
        return;
    }

}