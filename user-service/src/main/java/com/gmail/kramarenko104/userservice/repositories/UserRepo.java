package com.gmail.kramarenko104.userservice.repositories;

import com.gmail.kramarenko104.userservice.models.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends ReactiveMongoRepository <User, String> {
}