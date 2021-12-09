package com.thaichicken.cinemabooking.model;

import javax.persistence.*;

@Entity
@Table(name = "ShowTimeSeat", schema = "public", catalog = "pis-db")
@IdClass(ShowTimeSeatEntityPK.class)
public class ShowTimeSeatEntity {
    private int showtimeId;
    private int rowNumber;
    private int seatInRowNumber;
    private int cinemaHallNumber;

    @Id
    @Column(name = "showtime_id", nullable = false)
    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    @Id
    @Column(name = "row_number", nullable = false)
    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    @Id
    @Column(name = "seat_in_row_number", nullable = false)
    public int getSeatInRowNumber() {
        return seatInRowNumber;
    }

    public void setSeatInRowNumber(int seatInRowNumber) {
        this.seatInRowNumber = seatInRowNumber;
    }

    @Id
    @Column(name = "cinema_hall_number", nullable = false)
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

        ShowTimeSeatEntity that = (ShowTimeSeatEntity) o;

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
