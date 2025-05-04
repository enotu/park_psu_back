package ru.psu.amyum.park.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "places")
public class Place {
    @EmbeddedId
    private PlaceId id;
    @Column(name = "is_occupied")
    private Boolean isOccupied;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "booking_time")
    private Timestamp bookingTime;

    public Place(PlaceId id, Boolean isOccupied, Integer userId, Timestamp bookingTime) {
        this.id = id;
        this.isOccupied = isOccupied;
        this.userId = userId;
        this.bookingTime = bookingTime;
    }

    public Place() {}

    public PlaceId getId() {
        return id;
    }

    public void setId(PlaceId id) {
        this.id = id;
    }

    public Integer getPlaceNumber() {
        return id.getPlaceNumber();
    }

    public void setPlaceNumber(Integer placeNumber) {
        id.setPlaceNumber(placeNumber);
    }

    public Integer getParkingId() {
        return id.getParkingId();
    }

    public void setParkingId(Integer parkingId) {
        id.setParkingId(parkingId);
    }

    public Boolean getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(Boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Timestamp bookingTime) {
        this.bookingTime = bookingTime;
    }
}
