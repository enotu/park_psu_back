package ru.psu.amyum.park.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "booking_logs")
public class BookingLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "parking_id")
    private Integer parkingId;
    @Column(name = "place_number")
    private Integer placeNumber;
    @Column(name = "booked_at")
    private Timestamp bookedAt;
    @Column(name = "released_at")
    private Timestamp releasedAt;

    public BookingLog(Integer id, Integer userId, Integer parkingId, Integer placeNumber, Timestamp bookedAt, Timestamp releasedAt) {
        this.id = id;
        this.userId = userId;
        this.parkingId = parkingId;
        this.placeNumber = placeNumber;
        this.bookedAt = bookedAt;
        this.releasedAt = releasedAt;
    }

    public BookingLog() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer user_id) {
        this.userId = user_id;
    }

    public Integer getParkingId() {
        return parkingId;
    }

    public void setParkingId(Integer parking_id) {
        this.parkingId = parking_id;
    }

    public Integer getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(Integer place_number) {
        this.placeNumber = place_number;
    }

    public Timestamp getBookedAt() {
        return bookedAt;
    }

    public void setBookedAt(Timestamp booked_at) {
        this.bookedAt = booked_at;
    }

    public Timestamp getReleasedAt() {
        return releasedAt;
    }

    public void setReleasedAt(Timestamp released_at) {
        this.releasedAt = released_at;
    }
}
