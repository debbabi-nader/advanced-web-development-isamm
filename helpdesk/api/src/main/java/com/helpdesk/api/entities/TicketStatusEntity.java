package com.helpdesk.api.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.helpdesk.api.enumerations.TicketStatusEnum;


@Entity
@Table(name = "TICKET_STATUSES")
public class TicketStatusEntity implements Serializable {

    private static final long serialVersionUID = 2768026940465246345L;

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatusEnum status;

    @Column(nullable = false)
    private Instant timestamp;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "TICKET_ID")
    private TicketEntity ticket;

    public TicketStatusEntity() {
        super();
    }

    public TicketStatusEntity(
            String id,
            TicketStatusEnum status,
            Instant timestamp,
            TicketEntity ticket) {

        this.id = id;
        this.status = status;
        this.timestamp = timestamp;
        this.ticket = ticket;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TicketStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TicketStatusEnum status) {
        this.status = status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public TicketEntity getTicket() {
        return ticket;
    }

    public void setTicket(TicketEntity ticket) {
        this.ticket = ticket;
    }

}
