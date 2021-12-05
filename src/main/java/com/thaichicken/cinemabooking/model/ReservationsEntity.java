package com.thaichicken.cinemabooking.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Reservations", schema = "public", catalog = "pis-db")
public class ReservationsEntity {
    private int reservationId;
    private Timestamp timestamp;

    @Id
    @Column(name = "reservation_id", nullable = false)
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    @Basic
    @Column(name = "timestamp", nullable = false)
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationsEntity that = (ReservationsEntity) o;

        if (reservationId != that.reservationId) return false;
        return timestamp != null ? timestamp.equals(that.timestamp) : that.timestamp == null;
    }

    @Override
    public int hashCode() {
        int result = reservationId;
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }
}
