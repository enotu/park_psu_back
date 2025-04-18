package ru.psu.amyum.park.dto;


import java.time.LocalDate;

public class BookingRequest {
    private Integer placeId;
    private Integer parkingId;
    private LocalDate date;
    private String start_time;
    private Integer parking_time;

    public BookingRequest(Integer placeId, Integer parkingId, LocalDate date, String start_time, Integer parking_time) {
        this.placeId = placeId;
        this.parkingId = parkingId;
        this.date = date;
        this.start_time = start_time;
        this.parking_time = parking_time;
    }

    public BookingRequest() {}

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public Integer getParkingId() {
        return parkingId;
    }

    public void setParkingId(Integer parkingId) {
        this.parkingId = parkingId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public Integer getParking_time() {
        return parking_time;
    }

    public void setParking_time(Integer parking_time) {
        this.parking_time = parking_time;
    }
}
