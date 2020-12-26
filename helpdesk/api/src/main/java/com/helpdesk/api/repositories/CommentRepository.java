package com.helpdesk.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helpdesk.api.entities.CommentEntity;


@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, String> {
    
}
