package ru.psu.amyum.park.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.psu.amyum.park.dto.JwtAuthenticationDto;
import ru.psu.amyum.park.dto.RefreshTokenDto;
import ru.psu.amyum.park.dto.UserCredentialsDto;
import ru.psu.amyum.park.dto.UserDto;
import ru.psu.amyum.park.service.UserService;

import javax.naming.AuthenticationException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationDto> login(@RequestBody UserCredentialsDto userCredentialsDto) {
        try {
            JwtAuthenticationDto jwtAuthenticationDto = userService.singIn(userCredentialsDto);
            return ResponseEntity.ok(jwtAuthenticationDto);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<JwtAuthenticationDto> register(@RequestBody UserDto userDto) throws AuthenticationException {
        userService.register(userDto);
        JwtAuthenticationDto jwtAuthenticationDto = userService.singIn(new UserCredentialsDto(userDto.getEmail(), userDto.getPassword()));
        return ResponseEntity.ok(jwtAuthenticationDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationDto> refresh(@RequestBody RefreshTokenDto refreshTokenDto) {
        try {
            JwtAuthenticationDto jwtAuthenticationDto = userService.refreshToken(refreshTokenDto);
            return ResponseEntity.ok(jwtAuthenticationDto);
        } catch (Exception e) {
            return ResponseEntity.status(403).body(null);
        }
    }
}