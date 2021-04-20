package com.tacs.tacs.api;


import com.tacs.tacs.model.requestModel.CardModel;
import com.tacs.tacs.model.requestModel.*;

import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

@RequestMapping("api/decks")
@RestController
public class Deck {

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtener un deck por ID")
    public String getDeck(@PathVariable("id") int id){
        return "este es el deck numero " + id + "\n Sus cartas son: ";
    }

    @PostMapping("/{id}/cards")
    @ApiOperation(value = "Agregar una carta al deck de id")
    public int addCard(@PathVariable("id") int id, @Validated @NonNull @RequestBody CardModel card){
        return 1;
    }

    @DeleteMapping("/{id}/cards/{card_id}")
    @ApiOperation(value = "Borrar una carta id al deck de id")
    public int deleteCard(@PathVariable("id") int id, @PathVariable("card_id") int card_id){
        return 1;
    }
   
    @PostMapping
    @ApiOperation(value = "Crear deck")
    public int newDeck(@Validated @NonNull @RequestBody DeckModel deck){
        return 1;
    }

    @PatchMapping
    @ApiOperation(value = "Modificar el nombre de un deck")
    public DeckModel modifyDeck(@Validated @NonNull @RequestBody DeckModel deck){
        deck.nombre = "Te cambie el nombre";
        return deck;
    }
    
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Borrar un deck por ID")
    public int deleteDeck(@PathVariable("id") int id){
        return 0;
    }

}