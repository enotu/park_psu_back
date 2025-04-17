package ru.psu.amyum.park.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.psu.amyum.park.model.BookingLog;
import ru.psu.amyum.park.model.Place;
import ru.psu.amyum.park.repository.BookingLogRepository;
import ru.psu.amyum.park.repository.PlaceRepository;
import java.sql.Timestamp;

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
    public void bookPlace(int placeNumber, int parkingId, int userId, Timestamp bookingTime, int durationMinutes) {
        Place place = placeRepository.findById_PlaceNumberAndId_ParkingId(placeNumber, parkingId)
                .orElseThrow(() -> new RuntimeException("Место не найдено"));

        if (place.getIsOccupied() == Boolean.TRUE) {
            throw new RuntimeException("Место уже занято");
        }

        place.setIsOccupied(true);
        place.setBookingTime(bookingTime);
        place.setUserId(userId);
        placeRepository.save(place);

        BookingLog log = new BookingLog();

        log.setUserId(userId);
        log.setParkingId(parkingId);
        log.setPlaceNumber(placeNumber);
        log.setBookedAt(bookingTime);
        log.setReleasedAt(Timestamp.valueOf(bookingTime.toLocalDateTime().plusMinutes(durationMinutes)));
        bookingLogRepository.save(log);
    }
}

