package ru.psu.amyum.park.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.psu.amyum.park.model.User;
import ru.psu.amyum.park.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            throw new IllegalArgumentException("Пользователь с этим email уже существует");
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
        } else {
            throw new IllegalArgumentException("Пользователь с таким id:" + id + " не найден");
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public void updateUser(Long id, String email) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
        } else {
            throw new IllegalArgumentException("Пользователь с таким id:" + id + " не найден");
        }
        User user = optionalUser.get();

        if (email != null && !email.equals(user.getEmail())) {
            Optional<User> foundByEmail = userRepository.findByEmail(email);
            if (foundByEmail.isPresent()) {
                throw new IllegalArgumentException("Пользователь с этим email уже существует");
            }
            user.setEmail(email);
        }
        userRepository.save(user);
    }
}
