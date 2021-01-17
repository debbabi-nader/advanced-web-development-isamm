package com.helpdesk.api.services.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helpdesk.api.entities.TicketEntity;
import com.helpdesk.api.entities.TicketStatusEntity;
import com.helpdesk.api.enumerations.TicketStatusEnum;
import com.helpdesk.api.exceptions.ResourceNotFoundException;
import com.helpdesk.api.repositories.TicketRepository;
import com.helpdesk.api.services.TicketService;


@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Page<TicketEntity> findTickets(int page, int size, String creatorId) {
        return this.ticketRepository.findTickets(PageRequest.of(page, size), creatorId);
    }

    @Override
    public TicketEntity findTicketById(String ticketId) {

        return this.ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException(ticketId));

    }

    @Override
    public TicketEntity createTicket(TicketEntity ticketEntity) {

        ticketEntity.setId(UUID.randomUUID().toString());

        TicketStatusEntity openTicketStatus = new TicketStatusEntity(
                UUID.randomUUID().toString(),
                TicketStatusEnum.OPEN,
                Instant.now(),
                ticketEntity);

        List<TicketStatusEntity> ticketStatuses = new ArrayList<>();
        ticketStatuses.add(openTicketStatus);
        ticketEntity.setStatuses(ticketStatuses);

        return this.ticketRepository.saveAndFlush(ticketEntity);

    }

    @Override
    @Transactional
    public TicketEntity updateTicket(String ticketId, TicketEntity ticketEntity) {

        TicketEntity presistedTicketEntity = this.findTicketById(ticketId);

        presistedTicketEntity.setLabel(ticketEntity.getLabel());
        presistedTicketEntity.setDescription(ticketEntity.getDescription());

        return presistedTicketEntity;

    }

    @Override
    public TicketEntity updateTicketStatus(String ticketId, TicketStatusEntity ticketStatusEntity) {

        TicketEntity presistedTicketEntity = this.findTicketById(ticketId);

        ticketStatusEntity.setId(UUID.randomUUID().toString());
        ticketStatusEntity.setTimestamp(Instant.now());
        ticketStatusEntity.setTicket(presistedTicketEntity);
        presistedTicketEntity.getStatuses().add(ticketStatusEntity);

        return this.ticketRepository.saveAndFlush(presistedTicketEntity);

    }

    @Override
    public void deleteTicket(String ticketId) {
        this.ticketRepository.deleteById(ticketId);
    }

}
