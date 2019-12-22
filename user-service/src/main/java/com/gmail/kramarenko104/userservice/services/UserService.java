package com.gmail.kramarenko104.userservice.services;

import com.gmail.kramarenko104.userservice.models.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> createUser(User user);

    Mono<User> getUser(String id);

    Mono<User> getUserByLogin(String login);

    Mono<User> updateUser(User user);

    Mono<Void> deleteUserById(String id);

    Flux<User> findAllUsers();

}
