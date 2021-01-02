package com.helpdesk.api.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.helpdesk.api.entities.TicketStatusEntity;
import com.helpdesk.api.enumerations.TicketStatusEnum;


public class TicketStatusCreationDTO implements Serializable {

    private static final long serialVersionUID = 625209439151744592L;

    @NotNull
    private TicketStatusEnum status;

    public TicketStatusCreationDTO() {
        super();
    }

    public TicketStatusCreationDTO(TicketStatusEnum status) {
        this.status = status;
    }

    public void setStatus(TicketStatusEnum status) {
        this.status = status;
    }

    public TicketStatusEnum getStatus() {
        return this.status;
    }

    public TicketStatusEntity toTicketStatusEntity() {
        return new TicketStatusEntity(this.status);
    }

}
