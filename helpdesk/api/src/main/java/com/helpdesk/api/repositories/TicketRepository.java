package com.helpdesk.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helpdesk.api.entities.TicketEntity;


@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, String> {

    @Query("SELECT ticket FROM TicketEntity AS ticket WHERE ticket.createdBy.id LIKE :id")
    public Page<TicketEntity> findTicketsByCreatorId(@Param("id") String creatorId, Pageable pageable);

}
