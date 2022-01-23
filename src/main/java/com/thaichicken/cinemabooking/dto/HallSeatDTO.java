package com.thaichicken.cinemabooking.dto;

import lombok.Data;

@Data
public class HallSeatDTO {
    private Integer rowNumber;
    private Integer seatInRowNumber;
    private Integer cinemaHallNumber;
}
