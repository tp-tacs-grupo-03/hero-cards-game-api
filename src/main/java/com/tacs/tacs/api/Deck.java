package com.tacs.tacs.api;
import java.util.UUID;

import com.tacs.tacs.model.responseModel.BattleModel;
import com.tacs.tacs.model.responseModel.MatchModel;
import com.tacs.tacs.model.requestModel.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/decks")
@RestController
public class Deck {

    @GetMapping("/{id}")
    public String getDeck(@PathVariable("id") int id){
        return "este es el deck numero " + id + "\n Sus cartas son: ";
    }
   
    @PostMapping
    public int newDeck(@Validated @NonNull @RequestBody DeckModel deck){
        return 1;
    }

    @PatchMapping
    public DeckModel modifyDeck(@Validated @NonNull @RequestBody DeckModel deck){
        deck.nombre = "Te cambie el nombre";
        return deck;
    }
    
    @DeleteMapping("/{id}")
    public int deleteDeck(@PathVariable("id") int id){
        return 0;
    }

}