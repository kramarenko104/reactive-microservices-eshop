package com.gmail.kramarenko104.userservice.repositories;

import com.gmail.kramarenko104.userservice.models.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepo extends ReactiveMongoRepository <User, String> {
    Mono<User>  getUserByLogin(String login);
}