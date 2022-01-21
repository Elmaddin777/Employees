package com.bank.employees.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Transient;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FullnamesDto {

    @JsonIgnore
    private String firstName;

    @JsonIgnore
    private String lastName;

    @Transient
    private String fullName;

    public String getFullName(){
        return  this.firstName + " " + this.lastName;
    }
}
