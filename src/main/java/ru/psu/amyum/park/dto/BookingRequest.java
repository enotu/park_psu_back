package ru.psu.amyum.park.dto;

public class BookingRequest {
    private Integer placeId;
    private String date;
    private String start_time;
    private Integer parking_time;

    public BookingRequest(Integer placeId, String date, String start_time, Integer parking_time) {
        this.placeId = placeId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
