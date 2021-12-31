package com.thaichicken.cinemabooking.dto;

import lombok.Data;

@Data
public class CinemaHallDTO {
    private int cinemaHallNumber;
    private int capacity;
    private int nRows;
    private int nSeatsInRows;
}
