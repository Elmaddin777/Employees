package com.bank.employees.service;

import com.bank.employees.entity.Card;

import java.util.List;

public interface CardService {

    List<Card> getAllCards();

    Card saveOrUpdate(Card card);
}
