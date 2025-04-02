package ru.psu.amyum.park;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ParkingController {

    @GetMapping("/parkingList")
    public List<Parking> getParkingList() {
        return List.of(
                new Parking("Парковый, 50", 100, 15),
                new Parking("Сибирская, 46", 100, 60),
                new Parking("Мира, 8", 100, 78)
        );
    }
}