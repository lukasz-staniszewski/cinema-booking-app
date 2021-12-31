package com.thaichicken.cinemabooking.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "show_time", schema = "public", catalog = "pis-db")
public class ShowTimeEntity {
    private int showtimeId;
    private Date date;
    private Time hour;
    private MovieEntity movieByMovieId;
    private CinemaHallEntity cinemaHallByCinemaHallNumber;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "showTimeGenerator")
    @SequenceGenerator(name = "showTimeGenerator", sequenceName = "showtime_id_seq", allocationSize = 1)
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
        if (!Objects.equals(date, that.date)) return false;
        return Objects.equals(hour, that.hour);
    }

    @Override
    public int hashCode() {
        int result = showtimeId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (hour != null ? hour.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "movie_id", nullable = false)
    public MovieEntity getMovieByMovieId() {
        return movieByMovieId;
    }

    public void setMovieByMovieId(MovieEntity movieByMovieId) {
        this.movieByMovieId = movieByMovieId;
    }

    @ManyToOne
    @JoinColumn(name = "cinema_hall_number", referencedColumnName = "cinema_hall_number")
    public CinemaHallEntity getCinemaHallByCinemaHallNumber() {
        return cinemaHallByCinemaHallNumber;
    }

    public void setCinemaHallByCinemaHallNumber(CinemaHallEntity cinemaHall) {
        this.cinemaHallByCinemaHallNumber = cinemaHall;
    }
}
