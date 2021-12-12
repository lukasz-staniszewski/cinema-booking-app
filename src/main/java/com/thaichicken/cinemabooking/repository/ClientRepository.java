package com.thaichicken.cinemabooking.repository;

import com.thaichicken.cinemabooking.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
    Optional<ClientEntity> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE ClientEntity a SET a.enabled = TRUE WHERE a.email = ?1")

    int enableClientEntity(String email);
}
