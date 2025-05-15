package ru.psu.amyum.park.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Spot {
    private int id;
    private boolean isOccupied;
    private Date parkingEndTime; // или LocalDateTime, если нужно

    public Spot(int id, boolean isOccupied, Date parkingEndTime) {
        this.id = id;
        this.isOccupied = isOccupied;
        this.parkingEndTime = parkingEndTime;
    }

}