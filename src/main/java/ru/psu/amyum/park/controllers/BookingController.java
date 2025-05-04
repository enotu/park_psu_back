package ru.psu.amyum.park.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.psu.amyum.park.dto.BookingRequest;
import ru.psu.amyum.park.service.BookingService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@RequestMapping(path = "/api")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/booking")
    public ResponseEntity<String> bookPlace(@RequestBody BookingRequest request) {
        LocalTime startTime = LocalTime.parse(request.getStart_time());
        LocalDateTime startDateTime = LocalDateTime.of(request.getDate(), startTime);
        Timestamp bookingTime = Timestamp.valueOf(startDateTime);

        int placeId = request.getPlaceId();
        int userId = 1;     // пока что пусть 1
        int parkingId = request.getParkingId();
        int parkingTime = request.getParking_time();

        System.out.println(placeId);
        System.out.println(userId);
        System.out.println(parkingId);
        System.out.println(parkingTime);

        bookingService.bookPlace(placeId, parkingId, userId, bookingTime, parkingTime);
        return ResponseEntity.ok("Booking received");
    }
}
