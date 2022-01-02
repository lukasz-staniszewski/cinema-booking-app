package com.thaichicken.cinemabooking.dto;

import lombok.Data;

@Data
public class HallSeatDTO {
    private int rowNumber;
    private int seatInRowNumber;
    private int cinemaHallNumber;
}
