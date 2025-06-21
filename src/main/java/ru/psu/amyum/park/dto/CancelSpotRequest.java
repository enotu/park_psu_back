package ru.psu.amyum.park.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CancelSpotRequest {
    private Long spotId;
    private Long parkingId;
    private LocalDateTime startTime;
}
