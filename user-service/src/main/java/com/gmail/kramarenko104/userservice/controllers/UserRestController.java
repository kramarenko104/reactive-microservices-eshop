package com.gmail.kramarenko104.userservice.controllers;

import com.gmail.kramarenko104.userservice.models.User;
import com.gmail.kramarenko104.userservice.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserRestController {

    UserServiceImpl userService;

    @Autowired
    public UserRestController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public Flux<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{userId}")
    public Mono<ResponseEntity<User>> getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId)
                .map(foundUser -> ResponseEntity.ok(foundUser))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/api/{userId}")
    public Mono<User> getUserAPI(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }

    @GetMapping(params = {"login"})
    public Mono<ResponseEntity<User>> getUserByLogin(@RequestBody String login) {
        return userService.getUserByLogin(login)
                .map(foundUser -> ResponseEntity.ok(foundUser))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}")
    public Mono<ResponseEntity<User>> updateUser(@PathVariable("userId") String userId,
                                                 @RequestBody User user) {
        return userService.getUser(userId)
                .flatMap(foundUser ->
                        userService.updateUser(user)
                                .then(Mono.just(ResponseEntity.ok(user)))
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{userId}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable("userId") String userId) {
         return userService.getUser(userId)
                 .flatMap(foundUser ->
                    userService.deleteUserById(userId)
                         .then(Mono.just(ResponseEntity.ok().<Void>build()))
                 )
                 .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
