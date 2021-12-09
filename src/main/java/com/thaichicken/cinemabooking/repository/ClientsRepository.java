package com.thaichicken.cinemabooking.repository;

import com.thaichicken.cinemabooking.model.ClientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<ClientsEntity, Long> {
}
