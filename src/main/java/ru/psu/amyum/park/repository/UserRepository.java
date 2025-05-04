package ru.psu.amyum.park.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.psu.amyum.park.model.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users where email = :email", nativeQuery = true)
    Optional<User> findByEmail(String email);

}
