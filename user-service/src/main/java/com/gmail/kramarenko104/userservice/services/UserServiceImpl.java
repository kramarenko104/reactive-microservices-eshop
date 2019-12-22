package com.gmail.kramarenko104.userservice.services;

import com.gmail.kramarenko104.userservice.models.User;
import com.gmail.kramarenko104.userservice.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService {

    private final static String SALT = "34Ru9k";
    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Mono<User> createUser(User user) {
        User criptUser = user;
        criptUser.setPassword(hashString(user.getPassword()));
        return userRepo.insert(criptUser);
    }

    @Override
    public Mono<User> getUser(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Mono<User> getUserByLogin(String login) {
        return userRepo.getUserByLogin(login);
    }

    @Override
    public Mono<User> updateUser(User newUser) {
        return userRepo.save(newUser);
    }

    @Override
    public Mono<Void> deleteUserById(String id) {
         return userRepo.deleteById(id);
    }

    @Override
    public Flux<User> findAllUsers() {
        return userRepo.findAll();
    }

    private String hashString(String hash) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md5.update(StandardCharsets.UTF_8.encode(hash + SALT));
        return String.format("%032x", new BigInteger(md5.digest()));
    }

}
