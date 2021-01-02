package com.helpdesk.api.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.helpdesk.api.entities.TicketEntity;


public final class TicketDTO implements Serializable {

    private static final long serialVersionUID = -5742492666479361016L;

    private final String id;

    private final String label;

    private final String description;

    private final UserDTO createdBy;

    private final List<TicketStatusDTO> statuses;

    private TicketDTO(
            String id,
            String label,
            String description,
            UserDTO createdBy,
            List<TicketStatusDTO> statuses) {

        this.id = id;
        this.label = label;
        this.description = description;
        this.createdBy = createdBy;
        this.statuses = statuses;

    }

    public String getId() {
        return this.id;
    }

    public String getLabel() {
        return this.label;
    }

    public String getDescription() {
        return this.description;
    }

    public UserDTO getCreatedBy() {
        return this.createdBy;
    }

    public List<TicketStatusDTO> getStatuses() {
        return this.statuses;
    }

    public static TicketDTO from(TicketEntity ticketEntity) {

        return new TicketDTO(
                ticketEntity.getId(),
                ticketEntity.getLabel(),
                ticketEntity.getDescription(),
                UserDTO.from(ticketEntity.getCreatedBy()),
                ticketEntity.getStatuses()
                        .stream()
                        .map(TicketStatusDTO::from)
                        .collect(Collectors.toList()));

    }

}
