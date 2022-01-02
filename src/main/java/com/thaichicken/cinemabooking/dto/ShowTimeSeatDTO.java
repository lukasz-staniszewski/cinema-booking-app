package com.thaichicken.cinemabooking.dto;

import lombok.Data;

@Data
public class ShowTimeSeatDTO {
    private int showtimeId;
    private int rowNumber;
    private int seatInRowNumber;
    private int cinemaHallNumber;
    private int reservationId;
}
