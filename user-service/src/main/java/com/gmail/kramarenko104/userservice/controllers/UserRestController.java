package com.gmail.kramarenko104.userservice.controllers;

import com.gmail.kramarenko104.userservice.models.User;
import com.gmail.kramarenko104.userservice.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserRestController {

    UserServiceImpl userService;

    @Autowired
    public UserRestController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping()
    public Flux<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping()
        public Mono<User> createUser(@RequestParam("user") User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{userId}")
    public Optional<ResponseEntity<User>> getUser(@PathVariable("userId") long userId) {
        return userService.getUser(userId)
                .map(foundUser -> ResponseEntity.ok(foundUser))
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .blockOptional();
    }

    @GetMapping("/api/{userId}")
    public Mono<ResponseEntity<User>> getUserAPI(@PathVariable("userId") long userId) {
        return userService.getUser(userId)
                .map(foundUser -> ResponseEntity.ok(foundUser))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(params = {"login"})
    public Flux<User> getUserByLogin(@RequestParam("login") String login) {
        return userService.getUserByLogin(login);
    }

    @PutMapping
    public Mono<User> updateUser(@RequestParam("user") User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") long userId) {
         userService.deleteUserById(userId);
    }
}
