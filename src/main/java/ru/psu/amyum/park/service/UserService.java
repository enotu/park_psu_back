package ru.psu.amyum.park.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.psu.amyum.park.repository.User;
import ru.psu.amyum.park.repository.UserRepository;
import ru.psu.amyum.park.exception.EmailAlreadyExistsException;
import ru.psu.amyum.park.security.JwtTokenProvider;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String registerUser(String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException("Пользователь с таким email уже существует");
        }
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(null, email, encodedPassword);
        userRepository.save(user);

        // Генерация JWT токена
        return jwtTokenProvider.generateToken(email);
    }
}