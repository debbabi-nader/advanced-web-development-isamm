package com.helpdesk.api.dto;

import java.io.Serializable;
import java.time.Instant;

import com.helpdesk.api.entities.TicketStatusEntity;
import com.helpdesk.api.enumerations.TicketStatusEnum;


public final class TicketStatusDTO implements Serializable {

    private static final long serialVersionUID = -2828428041769120861L;

    private final String id;

    private final TicketStatusEnum status;

    private final Instant timestamp;

    private TicketStatusDTO(
            String id,
            TicketStatusEnum status,
            Instant timestamp) {

        this.id = id;
        this.status = status;
        this.timestamp = timestamp;

    }

    public String getId() {
        return this.id;
    }

    public TicketStatusEnum getStatus() {
        return this.status;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }

    public static TicketStatusDTO from(TicketStatusEntity ticketStatusEntity) {

        return new TicketStatusDTO(
                ticketStatusEntity.getId(),
                ticketStatusEntity.getStatus(),
                ticketStatusEntity.getTimestamp());

    }

}
