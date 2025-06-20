package ru.psu.amyum.park.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.psu.amyum.park.model.BookingLog;

import java.time.LocalDateTime;
import java.util.Optional;

public interface BookingLogRepository extends JpaRepository<BookingLog, Long> {
    Optional<BookingLog> findBySpotIdAndParkingIdAndStartTimeAndUsername(
            Long spotId, Long parkingId, LocalDateTime startTime, String username
    );
}
