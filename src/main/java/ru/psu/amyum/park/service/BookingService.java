package ru.psu.amyum.park.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.psu.amyum.park.dto.BookingRequest;
import ru.psu.amyum.park.model.BookingLog;
import ru.psu.amyum.park.model.Place;
import ru.psu.amyum.park.repository.BookingLogRepository;
import ru.psu.amyum.park.repository.PlaceRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class BookingService {

    private final PlaceRepository placeRepository;
    private final BookingLogRepository bookingLogRepository;

    public BookingService(PlaceRepository placeRepository,
                          BookingLogRepository bookingLogRepository) {
        this.placeRepository = placeRepository;
        this.bookingLogRepository = bookingLogRepository;
    }

    @Transactional
    public void bookPlace(int parkingId, int placeNumber, Timestamp bookingTime, int durationMinutes) {
        Place place = placeRepository.findById_PlaceNumberAndId_ParkingId(placeNumber, parkingId)
                .orElseThrow(() -> new RuntimeException("Место не найдено"));

        if (Boolean.TRUE.equals(place.getIsOccupied())) {
            throw new RuntimeException("Место уже занято");
        }

        place.setIsOccupied(true);
        place.setBookingTime(bookingTime);
        // Допустим пользователь определяется из сессии — здесь заглушка:
        place.setUserId(1);
        placeRepository.save(place);

        BookingLog log = new BookingLog();
        log.setUserId(1);
        log.setParkingId(parkingId);
        log.setPlaceNumber(placeNumber);
        log.setBookedAt(bookingTime);
        log.setReleasedAt(Timestamp.valueOf(bookingTime.toLocalDateTime().plusMinutes(durationMinutes)));

        bookingLogRepository.save(log);
    }


    public void bookFromDto(BookingRequest request) {
        // Преобразование даты и времени
        LocalDate bookingDate = LocalDate.parse(request.getDate());
        LocalTime startTime = LocalTime.parse(request.getStart_time());

        LocalDateTime startDateTime = LocalDateTime.of(bookingDate, startTime);
        Timestamp bookingTime = Timestamp.valueOf(startDateTime);

        // допустим placeId = 51 означает parking_id = 5, place_number = 1
        int placeId = request.getPlaceId();
        int parkingId = placeId / 10;
        int placeNumber = placeId % 10;

        // Вызов логики бронирования
        bookPlace(parkingId, placeNumber, bookingTime, request.getParking_time());
    }
}

