package com.bank.employees.service.impl;

import com.bank.employees.entity.Card;
import com.bank.employees.repository.CardRepository;
import com.bank.employees.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepo;

    @Override
    public List<Card> getAllCards() {
        return cardRepo.findAll();
    }

    @Override
    public Card saveOrUpdate(Card card) {
        return cardRepo.save(card);
    }
}
