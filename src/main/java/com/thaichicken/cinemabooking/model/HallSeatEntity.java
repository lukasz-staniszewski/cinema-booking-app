package com.thaichicken.cinemabooking.model;

import javax.persistence.*;

@Entity
@Table(name = "hall_seat", schema = "public", catalog = "pis-db")
@IdClass(HallSeatEntityPK.class)
public class HallSeatEntity {
    private int rowNumber;
    private int seatInRowNumber;
    private int cinemaHallNumber;
    private CinemaHallEntity cinemaHallByCinemaHallNumber;

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

        HallSeatEntity that = (HallSeatEntity) o;

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

    @ManyToOne
    @JoinColumn(name = "cinema_hall_number", referencedColumnName = "cinema_hall_number", nullable = false, insertable = false, updatable = false)
    public CinemaHallEntity getCinemaHallByCinemaHallNumber() {
        return cinemaHallByCinemaHallNumber;
    }

    public void setCinemaHallByCinemaHallNumber(CinemaHallEntity cinemaHallByCinemaHallNumber) {
        this.cinemaHallByCinemaHallNumber = cinemaHallByCinemaHallNumber;
    }
}
