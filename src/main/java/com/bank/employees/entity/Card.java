package com.bank.employees.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import javax.websocket.OnError;
import java.util.Date;

@Entity(name = "Card")
@Table(name = "employee_card")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Card {
    @JsonIgnore
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long Id;

    @Column(name = "card_number", unique = true)
    private Long cardNumber;

    @Column(name = "active", columnDefinition = "boolean default false")
    private Boolean active;

    @Column(name = "employee_id", unique = true)
    private Long employeeId;

    @Column(name = "issue_date", nullable = false, columnDefinition = "DATE")
    private Date issue_date;

    @Column(name = "end_date", columnDefinition = "DATE")
    private Date end_date;

    @JsonIgnore
    @OneToOne(mappedBy = "emp_card")
    Employee employee;
}
