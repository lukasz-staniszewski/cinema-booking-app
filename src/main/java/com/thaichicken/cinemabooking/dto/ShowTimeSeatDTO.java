package com.thaichicken.cinemabooking.dto;

import lombok.Data;

@Data
public class ShowTimeSeatDTO {
    private Integer showtimeId;
    private Integer rowNumber;
    private Integer seatInRowNumber;
    private Integer cinemaHallNumber;
    private Integer reservationId;
}
