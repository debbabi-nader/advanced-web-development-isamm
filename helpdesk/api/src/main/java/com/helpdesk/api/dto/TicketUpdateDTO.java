package com.helpdesk.api.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.helpdesk.api.entities.TicketEntity;


public final class TicketUpdateDTO implements Serializable {

    private static final long serialVersionUID = 8960359303992452047L;

    @NotBlank
    private final String label;

    @Size(max = 200)
    private final String description;

    public TicketUpdateDTO(String label, String description) {
        this.label = label;
        this.description = description;
    }

    public String getLabel() {
        return this.label;
    }

    public String getDescription() {
        return this.description;
    }

    public TicketEntity toTicketEntity() {
        return new TicketEntity(this.label, this.description);
    }

}
