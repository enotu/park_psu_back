package ru.psu.amyum.park;

public class Parking {
    private String location;
    private int parkings_average;
    private int parkings_free;

    public Parking(String location, int parkings_average, int parkings_free) {
        this.location = location;
        this.parkings_average = parkings_average;
        this.parkings_free = parkings_free;
    }

    public String getLocation() {
        return location;
    }

    public int getParkingsAverage() {
        return parkings_average;
    }

    public int getParkingsFree() {
        return parkings_free;
    }
}