package com.bank.employees.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Transient;

public class StatusDto {

    @JsonIgnore
    public boolean successful;

    @Transient
    public int status = 0;

    public StatusDto(boolean successful) {
        this.successful = successful;
        setStatus(successful);
    }

    public void setStatus(boolean successful){
        if (successful)
            this.status = 1;
    }
}
