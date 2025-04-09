package ru.psu.amyum.park.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.psu.amyum.park.repository.User;
import ru.psu.amyum.park.repository.UserRepository;
import ru.psu.amyum.park.exception.EmailAlreadyExistsException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException("Пользователь с таким email уже существует");
        }
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(null, email, encodedPassword);
        return userRepository.save(user);
    }
}