package ru.psu.amyum.park.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "booking_logs")
public class BookingLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer user_id;
    private Integer parking_id;
    private Integer place_number;
    private Timestamp booked_at;
    private Timestamp released_at;

    public BookingLog(Integer id, Integer user_id, Integer parking_id, Integer place_number, Timestamp booked_at, Timestamp released_at) {
        this.id = id;
        this.user_id = user_id;
        this.parking_id = parking_id;
        this.place_number = place_number;
        this.booked_at = booked_at;
        this.released_at = released_at;
    }

    public BookingLog() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getParking_id() {
        return parking_id;
    }

    public void setParking_id(Integer parking_id) {
        this.parking_id = parking_id;
    }

    public Integer getPlace_number() {
        return place_number;
    }

    public void setPlace_number(Integer place_number) {
        this.place_number = place_number;
    }

    public Timestamp getBooked_at() {
        return booked_at;
    }

    public void setBooked_at(Timestamp booked_at) {
        this.booked_at = booked_at;
    }

    public Timestamp getReleased_at() {
        return released_at;
    }

    public void setReleased_at(Timestamp released_at) {
        this.released_at = released_at;
    }
}
