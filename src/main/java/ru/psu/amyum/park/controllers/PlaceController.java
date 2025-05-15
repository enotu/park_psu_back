package ru.psu.amyum.park.controllers;

import org.springframework.web.bind.annotation.*;
import ru.psu.amyum.park.model.Place;
import ru.psu.amyum.park.service.PlaceService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/spotsForMapList")
    public List<Place> getSpotsForMapList(@RequestParam Integer parkingId) {
        return placeService.findByParkingId(parkingId);
    }
}