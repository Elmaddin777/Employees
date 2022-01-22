package com.bank.employees.controller;

import com.bank.employees.entity.Card;
import com.bank.employees.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/cards")
public class CardController {
    private final CardService cardService;

    @GetMapping
    public ResponseEntity<List<Card>> getAllCards(){
        var cards = cardService.getAllCards();
        return new ResponseEntity<List<Card>>(cards,HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Card> createOrUpdate(@RequestBody Card card){
        Card currCard = cardService.saveOrUpdate(card);
        return new ResponseEntity<Card>(card, HttpStatus.OK);
    }

}
