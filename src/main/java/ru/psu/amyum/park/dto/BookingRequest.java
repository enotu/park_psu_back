package ru.psu.amyum.park.dto;


import java.time.LocalDate;

public class BookingRequest {
    private Integer placeNumber;
    private Integer parkingId;
    private Integer userId;
    private LocalDate date;
    private String startTime;
    private Integer parkingTime;

    public BookingRequest(Integer placeNumber, Integer parkingId, Integer userId, LocalDate date, String startTime, Integer parkingTime) {
        this.placeNumber = placeNumber;
        this.parkingId = parkingId;
        this.userId = userId;
        this.date = date;
        this.startTime = startTime;
        this.parkingTime = parkingTime;
    }

    public BookingRequest() {}

    public Integer getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(Integer placeNumber) {
        this.placeNumber = placeNumber;
    }

    public Integer getParkingId() {
        return parkingId;
    }

    public void setParkingId(Integer parkingId) {
        this.parkingId = parkingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getParkingTime() {
        return parkingTime;
    }

    public void setParkingTime(Integer parkingTime) {
        this.parkingTime = parkingTime;
    }
}
