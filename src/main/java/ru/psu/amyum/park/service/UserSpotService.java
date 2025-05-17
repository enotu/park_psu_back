package ru.psu.amyum.park.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.psu.amyum.park.dto.SpotDto;
import ru.psu.amyum.park.model.Place;
import ru.psu.amyum.park.model.Parking;
import ru.psu.amyum.park.repository.ParkingRepository;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserSpotService {
    private final PlaceService placeService;
    private final ParkingRepository parkingRepository;

    public List<SpotDto> getUserSpots(int userId) {
        List<Place> places = placeService.findByUserId(userId);
        return places.stream().map(place -> {
            SpotDto dto = new SpotDto();
            dto.setTitle("Место №" + place.getPlaceNumber());
            Parking parking = parkingRepository.findById(place.getParkingId()).orElse(null);
            dto.setLocation(parking != null ? parking.getLocation() : "Неизвестно");
            dto.setSpotId(place.getPlaceNumber());
            dto.setParkingId(place.getParkingId());
            dto.setStartTime(place.getBookingTime() != null ? place.getBookingTime().toLocalDateTime() : null);
            dto.setEndTime(place.getParkingEndTime() != null ? place.getParkingEndTime().toLocalDateTime() : null);
            return dto;
        }).collect(Collectors.toList());
    }
}