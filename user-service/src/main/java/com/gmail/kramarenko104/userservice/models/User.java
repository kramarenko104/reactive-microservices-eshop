package com.gmail.kramarenko104.userservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User implements Serializable {

    @Id
    private long user_id;
    private String login;
    private String password;
    private String name;
    private String address;
    private String comment;
    private Cart cart;
    private List<Order> userOrders;
    private Set<Role> roles;

    @Override
    public String toString() {
        return "User{" +
                "userId:" + user_id + ", " +
                "login:'" + login + "', name:'" + name + "', roles: " + roles + "}";
    }
}