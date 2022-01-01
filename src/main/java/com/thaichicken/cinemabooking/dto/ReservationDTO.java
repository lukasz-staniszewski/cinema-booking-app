package com.thaichicken.cinemabooking.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReservationDTO {
    private int reservationId;
    private Timestamp timestamp;
    private int clientId;
    private String clientName;
    private String clientSurname;
    private String clientEmail;
    private String clientPhone;
}
