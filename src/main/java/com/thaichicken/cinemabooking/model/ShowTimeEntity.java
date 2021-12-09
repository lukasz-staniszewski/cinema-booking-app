package com.thaichicken.cinemabooking.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "ShowTime", schema = "public", catalog = "pis-db")
public class ShowTimeEntity {
    private int showtimeId;
    private Date date;
    private Time hour;

    @Id
    @Column(name = "showtime_id", nullable = false)
    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "hour", nullable = false)
    public Time getHour() {
        return hour;
    }

    public void setHour(Time hour) {
        this.hour = hour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShowTimeEntity that = (ShowTimeEntity) o;

        if (showtimeId != that.showtimeId) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return hour != null ? hour.equals(that.hour) : that.hour == null;
    }

    @Override
    public int hashCode() {
        int result = showtimeId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (hour != null ? hour.hashCode() : 0);
        return result;
    }
}
