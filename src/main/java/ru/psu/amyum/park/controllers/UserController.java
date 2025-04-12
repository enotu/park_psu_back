package ru.psu.amyum.park.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.psu.amyum.park.dto.TokenDto;
import ru.psu.amyum.park.dto.UserRegistrationDto;
import ru.psu.amyum.park.service.UserService;


@RestController
@RequestMapping(path = "/api/register")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<TokenDto> registerUser(@Valid @RequestBody UserRegistrationDto userDto) {
        String token = userService.registerUser(userDto.getEmail(), userDto.getPassword());
        return ResponseEntity.ok(new TokenDto(token));
    }
}