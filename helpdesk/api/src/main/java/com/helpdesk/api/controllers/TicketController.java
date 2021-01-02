package com.helpdesk.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.helpdesk.api.dto.PageDTO;
import com.helpdesk.api.dto.TicketCreationDTO;
import com.helpdesk.api.dto.TicketDTO;
import com.helpdesk.api.dto.TicketStatusCreationDTO;
import com.helpdesk.api.dto.TicketUpdateDTO;
import com.helpdesk.api.services.TicketService;


@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping(params = { "creatorId", "page", "size" })
    public PageDTO<TicketDTO> findTicketsByCreatorId(
            @RequestParam("creatorId") String creatorId,
            @RequestParam("page") int page,
            @RequestParam("size") int size) {

        return PageDTO.from(ticketService.findTicketsByCreatorId(creatorId, page, size).map(TicketDTO::from));

    }

    @GetMapping("/{ticketId}")
    public TicketDTO findTicketById(@PathVariable(name = "ticketId") String ticketId) {
        return TicketDTO.from(this.ticketService.findTicketById(ticketId));
    }

    @PostMapping()
    public TicketDTO createTicket(@RequestBody @Valid TicketCreationDTO ticketCreationDTO) {
        return TicketDTO.from(this.ticketService.createTicket(ticketCreationDTO.toTicketEntity()));
    }

    @PutMapping("/{ticketId}")
    public TicketDTO updateTicket(
            @PathVariable(name = "ticketId") String ticketId,
            @RequestBody @Valid TicketUpdateDTO ticketUpdateDTO) {

        return TicketDTO.from(this.ticketService.updateTicket(ticketId, ticketUpdateDTO.toTicketEntity()));

    }

    @PostMapping("/{ticketId}")
    public TicketDTO updateTicketStatus(
            @PathVariable(name = "ticketId") String ticketId,
            @RequestBody @Valid TicketStatusCreationDTO ticketStatusCreationDTO) {

        return TicketDTO.from(this.ticketService.updateTicketStatus(ticketId, ticketStatusCreationDTO.toTicketStatusEntity()));

    }

    @DeleteMapping("/{ticketId}")
    public void deleteTicket(@PathVariable(name = "ticketId") String ticketId) {
        this.ticketService.deleteTicket(ticketId);
    }

}
