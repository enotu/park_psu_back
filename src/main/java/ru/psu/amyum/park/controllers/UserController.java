package ru.psu.amyum.park.controllers;

import org.springframework.web.bind.annotation.*;
import ru.psu.amyum.park.repository.User;
import ru.psu.amyum.park.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
    }

    @PutMapping(path = "{id}")
    public void updateUser(
            @PathVariable(name = "id")  Long id,
            @RequestParam(required = false) String email
    ){
        userService.updateUser(id, email);
    }
}
