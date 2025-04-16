package ru.psu.amyum.park.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.psu.amyum.park.dto.BookingRequest;
import ru.psu.amyum.park.service.BookingService;

@RestController
@RequestMapping(path = "/api/booking")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<String> bookPlace(@RequestBody BookingRequest request) {
        try {
            bookingService.bookFromDto(request);
            return ResponseEntity.ok("Место успешно забронировано");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка бронирования: " + e.getMessage());
        }
    }
}
