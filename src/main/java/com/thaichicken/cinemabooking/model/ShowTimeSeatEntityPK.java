package com.thaichicken.cinemabooking.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ShowTimeSeatEntityPK implements Serializable {
    private int showtimeId;
    private int rowNumber;
    private int seatInRowNumber;
    private int cinemaHallNumber;

    public ShowTimeSeatEntityPK() {
    }

    public ShowTimeSeatEntityPK(int showtimeId, int rowNumber, int seatInRowNumber, int cinemaHallNumber) {
        this.showtimeId = showtimeId;
        this.rowNumber = rowNumber;
        this.seatInRowNumber = seatInRowNumber;
        this.cinemaHallNumber = cinemaHallNumber;
    }

    @Column(name = "showtime_id", nullable = false)
    @Id
    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    @Column(name = "row_number", nullable = false)
    @Id
    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    @Column(name = "seat_in_row_number", nullable = false)
    @Id
    public int getSeatInRowNumber() {
        return seatInRowNumber;
    }

    public void setSeatInRowNumber(int seatInRowNumber) {
        this.seatInRowNumber = seatInRowNumber;
    }

    @Column(name = "cinema_hall_number", nullable = false)
    @Id
    public int getCinemaHallNumber() {
        return cinemaHallNumber;
    }

    public void setCinemaHallNumber(int cinemaHallNumber) {
        this.cinemaHallNumber = cinemaHallNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShowTimeSeatEntityPK that = (ShowTimeSeatEntityPK) o;

        if (showtimeId != that.showtimeId) return false;
        if (rowNumber != that.rowNumber) return false;
        if (seatInRowNumber != that.seatInRowNumber) return false;
        return cinemaHallNumber == that.cinemaHallNumber;
    }

    @Override
    public int hashCode() {
        int result = showtimeId;
        result = 31 * result + rowNumber;
        result = 31 * result + seatInRowNumber;
        result = 31 * result + cinemaHallNumber;
        return result;
    }
}
