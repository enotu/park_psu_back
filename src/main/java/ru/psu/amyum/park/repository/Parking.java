package ru.psu.amyum.park.repository;

public class Parking {
    private Integer id;
    private String location;
    private String status;

    public Parking(Integer id, String location, String status) {
        this.id = id;
        this.location = location;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
