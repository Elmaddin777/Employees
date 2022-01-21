package com.bank.employees.mapper;

import com.bank.employees.dto.CardDto;
import com.bank.employees.entity.Card;

public class CardMapper {
    public static Card toCard(CardDto cardDto){
        if (cardDto == null)
            return null;

        return Card.builder()
                .Id(cardDto.getId())
                .cardNumber(cardDto.getCard_number())
                .employeeId(cardDto.getEmployee_id())
                .active(cardDto.getActive())
                .issue_date(cardDto.getIssue_date())
                .end_date(cardDto.getEnd_date())
                //.employee(EmployeeMapper.toEmployee(cardDto.getEmployee()))
                .build();
    }

    public static CardDto toCardDto(Card card){
        if (card == null)
            return null;

        return CardDto.builder()
                .Id(card.getId())
                .card_number(card.getCardNumber())
                .employee_id(card.getEmployeeId())
                .active(card.getActive())
                .issue_date(card.getIssue_date())
                .end_date(card.getEnd_date())
                //.employee(EmployeeMapper.toEmployeeDto(card.getEmployee()))
                .build();
    }

}
