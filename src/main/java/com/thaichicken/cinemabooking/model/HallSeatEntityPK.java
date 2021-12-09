package com.thaichicken.cinemabooking.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class HallSeatEntityPK implements Serializable {
    private int rowNumber;
    private int seatInRowNumber;
    private int cinemaHallNumber;

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

        HallSeatEntityPK that = (HallSeatEntityPK) o;

        if (rowNumber != that.rowNumber) return false;
        if (seatInRowNumber != that.seatInRowNumber) return false;
        return cinemaHallNumber == that.cinemaHallNumber;
    }

    @Override
    public int hashCode() {
        int result = rowNumber;
        result = 31 * result + seatInRowNumber;
        result = 31 * result + cinemaHallNumber;
        return result;
    }
}
