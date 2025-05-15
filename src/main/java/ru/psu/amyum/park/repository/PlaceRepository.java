package ru.psu.amyum.park.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.psu.amyum.park.model.Place;
import ru.psu.amyum.park.model.PlaceId;
import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, PlaceId> {
    List<Place> findAllByUserId(Integer userId);
    List<Place> findAllById_ParkingId(Integer parkingId);
    Optional<Place> findById_PlaceNumberAndId_ParkingId(Integer placeNumber, Integer parkingId);

}
