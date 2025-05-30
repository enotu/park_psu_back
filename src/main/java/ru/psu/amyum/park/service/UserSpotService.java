package ru.psu.amyum.park.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.psu.amyum.park.dto.SpotDto;
import ru.psu.amyum.park.model.BookingLog;
import ru.psu.amyum.park.model.Parking;
import ru.psu.amyum.park.repository.BookingLogRepository;
import ru.psu.amyum.park.repository.ParkingRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserSpotService {
    private final BookingLogRepository bookingLogRepository;
    private final ParkingRepository parkingRepository;

    public List<SpotDto> getUserSpots(int userId) {
        LocalDateTime now = LocalDateTime.now();
        List<BookingLog> bookings = bookingLogRepository.findByUserId(userId);
        return bookings.stream()
                .filter(log -> log.getReleasedAt() != null && log.getReleasedAt().toLocalDateTime().isAfter(now))
                .map(log -> {
                    SpotDto dto = new SpotDto();
                    dto.setTitle("Место №" + log.getPlaceNumber());
                    Parking parking = parkingRepository.findById(log.getParkingId()).orElse(null);
                    dto.setLocation(parking != null ? parking.getLocation() : "Неизвестно");
                    dto.setSpotId(log.getPlaceNumber());
                    dto.setParkingId(log.getParkingId());
                    dto.setStartTime(log.getBookedAt() != null ? log.getBookedAt().toLocalDateTime() : null);
                    dto.setEndTime(log.getReleasedAt() != null ? log.getReleasedAt().toLocalDateTime() : null);
                    return dto;
                })
                .collect(Collectors.toList());
    }
}