package com.thaichicken.cinemabooking.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReservationCreationDTO {
    private int reservationId;
    private Timestamp timestamp;
    private int clientId;
}
