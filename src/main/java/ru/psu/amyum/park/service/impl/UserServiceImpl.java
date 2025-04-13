package ru.psu.amyum.park.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.psu.amyum.park.dto.JwtAuthenticationDto;
import ru.psu.amyum.park.dto.RefreshTokenDto;
import ru.psu.amyum.park.dto.UserCredentialsDto;
import ru.psu.amyum.park.dto.UserDto;
import ru.psu.amyum.park.mapper.UserMapper;
import ru.psu.amyum.park.model.User;
import ru.psu.amyum.park.repository.UserRepository;
import ru.psu.amyum.park.security.Jwt.JwtService;
import ru.psu.amyum.park.service.UserService;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    @Override
    public JwtAuthenticationDto singIn(UserCredentialsDto userCredentialsDto) throws AuthenticationException {
        User user = findByCredentials(userCredentialsDto);
        return jwtService.generateAuthToken(user.getEmail());
    }

    @Override
    public JwtAuthenticationDto refreshToken(RefreshTokenDto refreshTokenDto) throws Exception {
        String refreshToken = refreshTokenDto.getRefreshToken();
        if (refreshToken != null && jwtService.validateJwtToken(refreshToken)) {
            User user = findByEmail(jwtService.getEmailFromToken(refreshToken));
            return jwtService.refreshBaseToken(user.getEmail(), refreshToken);
        }
        throw new AuthenticationException("Invalid refresh token");
    }

    @Override
    @Transactional
    public UserDto getUserByEmail(String email) {
        return userMapper.toDto(userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found")));
    }

    @Override
    public String register(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with this email already exists");
        }
        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }

    private User findByCredentials(UserCredentialsDto userCredentialsDto) throws AuthenticationException {
        Optional<User> optionalUser = userRepository.findByEmail(userCredentialsDto.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(userCredentialsDto.getPassword(), user.getPassword())) {
                return user;
            }
        }
        throw new AuthenticationException("Invalid email or password");
    }

    private User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}