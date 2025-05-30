package ru.psu.amyum.park.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.psu.amyum.park.model.BookingLog;
import ru.psu.amyum.park.model.Place;
import ru.psu.amyum.park.repository.BookingLogRepository;
import ru.psu.amyum.park.repository.PlaceRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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

    public boolean isBookingAvailable(int placeNumber, int parkingId, Timestamp newStart, Timestamp newEnd){
        List<BookingLog> bookings = bookingLogRepository.findByPlaceNumberAndParkingIdOrderByBookedAtDesc(placeNumber, parkingId);
        for (BookingLog booking : bookings) {
            Timestamp existingStart = booking.getBookedAt();
            Timestamp existingEnd = booking.getReleasedAt();
            if (newStart.before(existingEnd) && newEnd.after(existingStart)) {
                return false;
            }
        }
        return true;
    }

    @Transactional
    public void bookPlace(int placeNumber, int parkingId, int userId, Timestamp bookingTime, int durationMinutes) {
        Timestamp parkingEndTime = Timestamp.valueOf(bookingTime.toLocalDateTime().plusMinutes(durationMinutes));
        if (!isBookingAvailable(placeNumber, parkingId, bookingTime, parkingEndTime)) {
            throw new RuntimeException("Место занято в указанный период");
        }

        if (bookingTime.after(Timestamp.valueOf(LocalDateTime.now()))) {
            bookingLogRepository.save(createBookingLog(userId, parkingId, placeNumber, bookingTime, parkingEndTime, false));
            return;
        }

        Place place = placeRepository.findById_PlaceNumberAndId_ParkingId(placeNumber, parkingId)
                .orElseThrow(() -> new RuntimeException("Место не найдено"));


        if(place.getParkingEndTime() == null || bookingTime.after(place.getParkingEndTime()) || bookingTime.equals(place.getParkingEndTime())) {
            place.setIsOccupied(true);
            place.setBookingTime(bookingTime);
            place.setUserId(userId);
            place.setParkingEndTime(parkingEndTime);
            placeRepository.save(place);
        }

        bookingLogRepository.save(createBookingLog(userId, parkingId, placeNumber, bookingTime, parkingEndTime, true));
    }

    public List<BookingLog> getBookingsByPlaceAndParking(Integer placeNumber, Integer parkingId) {
        return bookingLogRepository.findByPlaceNumberAndParkingIdOrderByBookedAtDesc(placeNumber, parkingId);
    }

    private BookingLog createBookingLog(int userId, int parkingId, int placeNumber, Timestamp bookedAt, Timestamp releasedAt, boolean activated) {
        BookingLog log = new BookingLog();
        log.setUserId(userId);
        log.setParkingId(parkingId);
        log.setPlaceNumber(placeNumber);
        log.setBookedAt(bookedAt);
        log.setReleasedAt(releasedAt);
        log.setActivated(activated);
        return log;
    }

}