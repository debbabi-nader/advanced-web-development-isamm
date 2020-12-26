package com.helpdesk.api.services.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helpdesk.api.entities.TicketEntity;
import com.helpdesk.api.entities.TicketStatusEntity;
import com.helpdesk.api.enumerations.TicketStatusEnum;
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
    public Page<TicketEntity> findTicketsByCreatorId(String creatorId, Pageable pageable) {
        return this.ticketRepository.findTicketsByCreatorId(creatorId, pageable);
    }

    @Override
    public TicketEntity findTicketById(String ticketId) {
        return this.ticketRepository.findById(ticketId).orElseThrow(RuntimeException::new);
    }

    @Override
    public TicketEntity createTicket(TicketEntity ticketEntity) {

        ticketEntity.setId(UUID.randomUUID().toString());
        this.ticketRepository.saveAndFlush(ticketEntity);

        TicketStatusEntity openTicketStatus = new TicketStatusEntity(
                UUID.randomUUID().toString(),
                TicketStatusEnum.OPEN,
                Instant.now(),
                ticketEntity);

        List<TicketStatusEntity> ticketStatuses = new ArrayList<>();
        ticketStatuses.add(openTicketStatus);
        ticketEntity.setStatuses(ticketStatuses);

        return ticketEntity;

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

        ticketStatusEntity.setTicket(presistedTicketEntity);
        presistedTicketEntity.getStatuses().add(ticketStatusEntity);

        return this.ticketRepository.saveAndFlush(presistedTicketEntity);

    }

    @Override
    public void deleteTicket(String ticketId) {
        this.ticketRepository.deleteById(ticketId);
    }

}
