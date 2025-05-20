package ru.psu.amyum.park.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.psu.amyum.park.model.BookingLog;
import ru.psu.amyum.park.model.Place;
import ru.psu.amyum.park.repository.BookingLogRepository;
import ru.psu.amyum.park.repository.PlaceRepository;
import java.sql.Timestamp;
import java.util.List;


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

        if (Boolean.TRUE.equals(place.getIsOccupied())) {
            throw new RuntimeException("Место уже занято");
        }

        place.setIsOccupied(true);
        place.setBookingTime(bookingTime);
        place.setUserId(userId);
        Timestamp parkingEndTime = Timestamp.valueOf(bookingTime.toLocalDateTime().plusMinutes(durationMinutes));
        place.setParkingEndTime(parkingEndTime);

        placeRepository.save(place);

        bookingLogRepository.save(createBookingLog(userId, parkingId, placeNumber, bookingTime, parkingEndTime));
    }

    public List<BookingLog> getBookingsByPlaceAndParking(Integer placeNumber, Integer parkingId) {
        return bookingLogRepository.findByPlaceNumberAndParkingIdOrderByBookedAtDesc(placeNumber, parkingId);
    }

    private BookingLog createBookingLog(int userId, int parkingId, int placeNumber, Timestamp bookedAt, Timestamp releasedAt) {
        BookingLog log = new BookingLog();
        log.setUserId(userId);
        log.setParkingId(parkingId);
        log.setPlaceNumber(placeNumber);
        log.setBookedAt(bookedAt);
        log.setReleasedAt(releasedAt);
        return log;
    }

}