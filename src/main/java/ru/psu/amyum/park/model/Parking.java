package ru.psu.amyum.park.model;

import jakarta.persistence.*;
@Entity
@Table(name = "parkings")
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String location;
    private Integer parking_average;
    private Integer parking_free;

    public Parking(Integer id, String location, Integer parking_average, Integer parking_free) {
        this.id = id;
        this.location = location;
        this.parking_average = parking_average;
        this.parking_free = parking_free;
    }

    public Parking() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getParking_average() {
        return parking_average;
    }

    public void setParking_average(Integer parking_average) {
        this.parking_average = parking_average;
    }

    public Integer getParking_free() {
        return parking_free;
    }

    public void setParking_free(Integer parking_free) {
        this.parking_free = parking_free;
    }
}