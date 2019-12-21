package com.gmail.kramarenko104.userservice;

import com.gmail.kramarenko104.userservice.models.Role;
import com.gmail.kramarenko104.userservice.models.User;
import com.gmail.kramarenko104.userservice.repositories.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class UserServiceApplication  {

    private final static String SALT = "34Ru9k";

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserRepo userRepo) {
        Set<Role> rolesAU = new HashSet<>();
        rolesAU.add(new Role("ROLE_ADMIN"));
        rolesAU.add(new Role("ROLE_USER"));
        Set<Role> rolesU = new HashSet<>();
        rolesU.add(new Role("ROLE_USER"));

        return args -> { Flux.just(
                new User().toBuilder()
                    .name("Alex")
                    .user_id(0)
                    .login("admin")
                    .password(hashString("admin"))
                    .roles(rolesAU)
                    .address("-")
                    .comment("no comments")
                    .build(),
                new User().toBuilder()
                     .name("Alex")
                     .user_id(1)
                     .login("alex@gmail.com")
                     .password(hashString("1111111"))
                     .roles(rolesU)
                     .address("alex address")
                     .comment("don't call before delivery")
                     .build(),
                new User().toBuilder()
                    .name("Julia")
                    .user_id(2)
                    .login("juli@gmail.com")
                    .password(hashString("12345678"))
                    .roles(rolesU)
                    .address("julia address")
                    .comment("no comments")
                    .build())
                .flatMap(userRepo::save)
                .thenMany(userRepo.findAll())
                .subscribe(System.out::println);
        };
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

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("pl.piomin.services.employee.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().version("1.0").title("Employee API").description("Documentation Employee API v1.0").build());
    }
}

