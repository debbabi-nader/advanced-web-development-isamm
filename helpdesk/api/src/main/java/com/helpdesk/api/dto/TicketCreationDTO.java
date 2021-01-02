package com.helpdesk.api.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.helpdesk.api.entities.TicketEntity;
import com.helpdesk.api.entities.UserEntity;


public final class TicketCreationDTO implements Serializable {

    private static final long serialVersionUID = 625209439151744592L;

    @NotBlank
    private final String label;

    @Size(max = 200)
    private final String description;

    @NotBlank
    private final String creatorId;

    public TicketCreationDTO(
            String label,
            String description,
            String creatorId) {

        this.label = label;
        this.description = description;
        this.creatorId = creatorId;

    }

    public String getLabel() {
        return this.label;
    }

    public String getDescription() {
        return this.description;
    }

    public String getCreatorId() {
        return this.creatorId;
    }

    public TicketEntity toTicketEntity() {

        return new TicketEntity(
                this.label,
                this.description,
                new UserEntity(this.creatorId));

    }

}
