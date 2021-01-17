package com.helpdesk.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helpdesk.api.entities.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    @Query("SELECT user FROM UserEntity AS user WHERE user.email LIKE :email")
    public Optional<UserEntity> findUserByEmail(@Param("email") String email);

}
