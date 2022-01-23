package com.thaichicken.cinemabooking.repository;

import com.thaichicken.cinemabooking.model.ClientEntity;
import com.thaichicken.cinemabooking.model.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer> {
    List<ReservationEntity> findAllByClientByClientId(ClientEntity client);
}
