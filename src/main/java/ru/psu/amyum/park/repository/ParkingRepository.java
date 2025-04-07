package ru.psu.amyum.park.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ParkingRepository extends JpaRepository<Parking, Integer> {

}
