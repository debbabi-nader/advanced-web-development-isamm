package com.helpdesk.api.enumerations;


public enum TicketStatusEnum {

    OPEN("OPEN"),
    IN_PROGRESS("IN_PROGRESS"),
    BLOCKED("BLOCKED"),
    CLOSED("CLOSED");

    private final String ticketStatus;

    private TicketStatusEnum(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public String getTicketStatus() {
        return this.ticketStatus;
    }

}
