package ru.psu.amyum.park.controllers;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.psu.amyum.park.dto.BookingRequest;
import ru.psu.amyum.park.service.BookingService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.psu.amyum.park.service.UserService;
import ru.psu.amyum.park.dto.BookingResponse;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@RequestMapping(path = "/api")
public class BookingController {
    private final BookingService bookingService;
    private final UserService userService;

    public BookingController(BookingService bookingService, UserService userService) {
        this.bookingService = bookingService;
        this.userService = userService;
    }

    @PostMapping("/booking")
    public ResponseEntity<BookingResponse> bookPlace(@RequestBody BookingRequest request) throws ChangeSetPersister.NotFoundException {
        LocalTime startTime = LocalTime.parse(request.getStart_time());
        LocalDateTime startDateTime = LocalDateTime.of(request.getDate(), startTime);
        Timestamp bookingTime = Timestamp.valueOf(startDateTime);

        int placeId = request.getPlaceId();
        int parkingId = request.getParkingId();
        int parkingTime = request.getParking_time();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        int userId = userService.getUserByEmail(email).getId();

        bookingService.bookPlace(placeId, parkingId, userId, bookingTime, parkingTime);

        BookingResponse response = new BookingResponse(
                placeId,
                parkingId,
                request.getDate(),
                request.getStart_time(),
                parkingTime
        );
        return ResponseEntity.ok(response);
    }
}
