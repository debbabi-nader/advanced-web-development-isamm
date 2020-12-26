package com.helpdesk.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helpdesk.api.entities.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    
}
