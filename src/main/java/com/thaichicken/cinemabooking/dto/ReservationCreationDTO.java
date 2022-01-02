package com.thaichicken.cinemabooking.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ReservationCreationDTO {
    private int reservationId;
    private Timestamp timestamp;
    private String clientEmail;
    private int showTimeId;
    private List<HallSeatDTO> reservationSeats;
}
