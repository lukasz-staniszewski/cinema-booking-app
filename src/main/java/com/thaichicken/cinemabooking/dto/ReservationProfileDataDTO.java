package com.thaichicken.cinemabooking.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Data
public class ReservationProfileDataDTO {
    private Integer reservationId;
    private Timestamp timestamp;
    private Date date;
    private Time hour;
    private Integer hallNumber;
    private String movieName;
    private List<HallSeatDTO> seats;
    private Boolean isToCancel;
}
