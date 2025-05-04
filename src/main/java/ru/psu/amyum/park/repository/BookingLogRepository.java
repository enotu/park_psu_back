package ru.psu.amyum.park.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.psu.amyum.park.model.BookingLog;

public interface BookingLogRepository extends JpaRepository<BookingLog, Integer> {
}
