package com.thaichicken.cinemabooking.repository;

import com.thaichicken.cinemabooking.model.ClientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<ClientsEntity, Long> {
}
