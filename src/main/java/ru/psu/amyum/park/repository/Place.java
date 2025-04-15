package ru.psu.amyum.park.repository;

import java.sql.Timestamp;

public class Place {
    private Integer place_number;
    private Integer parking_id;
    private Boolean is_occupied;
    private Integer user_id;
    private Timestamp booking_time;

    public Place(Integer place_number, Integer parking_id, Boolean is_occupied, Integer user_id, Timestamp booking_time) {
        this.place_number = place_number;
        this.parking_id = parking_id;
        this.is_occupied = is_occupied;
        this.user_id = user_id;
        this.booking_time = booking_time;
    }

    public Place() {
    }

    public Integer getPlace_number() {
        return place_number;
    }

    public void setPlace_number(Integer place_number) {
        this.place_number = place_number;
    }

    public Integer getParking_id() {
        return parking_id;
    }

    public void setParking_id(Integer parking_id) {
        this.parking_id = parking_id;
    }

    public Boolean getIs_occupied() {
        return is_occupied;
    }

    public void setIs_occupied(Boolean is_occupied) {
        this.is_occupied = is_occupied;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Timestamp getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(Timestamp booking_time) {
        this.booking_time = booking_time;
    }
}
