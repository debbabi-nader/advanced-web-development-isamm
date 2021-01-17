package com.helpdesk.api.services;

import org.springframework.data.domain.Page;

import com.helpdesk.api.entities.TicketEntity;
import com.helpdesk.api.entities.TicketStatusEntity;


public interface TicketService {

    public Page<TicketEntity> findTickets(int page, int size, String creatorId);

    public TicketEntity findTicketById(String ticketId);

    public TicketEntity createTicket(TicketEntity ticketEntity);

    public TicketEntity updateTicket(String ticketId, TicketEntity ticketEntity);

    public TicketEntity updateTicketStatus(String ticketId, TicketStatusEntity ticketStatusEntity);

    public void deleteTicket(String ticketId);

}
