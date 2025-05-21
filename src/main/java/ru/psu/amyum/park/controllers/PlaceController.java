package ru.psu.amyum.park.controllers;

import org.springframework.web.bind.annotation.*;
import ru.psu.amyum.park.model.Place;
import ru.psu.amyum.park.service.PlaceService;
import ru.psu.amyum.park.dto.Spot;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/spotsForMapList")
    public List<Spot> getSpotsForMapList(@RequestParam Integer parkingId) {
        List<Place> places = placeService.findByParkingId(parkingId);
        return places.stream()
                .map(place -> new Spot(
                        place.getId(),
                        place.isOccupied(),
                        place.getParkingEndTime()
                ))
                .sorted(Comparator.comparing(Spot::getId))
                .collect(Collectors.toList());
    }
}