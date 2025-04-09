package ru.psu.amyum.park.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.psu.amyum.park.dto.UserRegistrationDto;
import ru.psu.amyum.park.repository.User;
import ru.psu.amyum.park.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserRegistrationDto userDto) {
        User user = userService.registerUser(userDto.getEmail(), userDto.getPassword());
        return ResponseEntity.ok(user);
    }
}