package com.gmail.kramarenko104.userservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Data
public class Cart implements Serializable {

    @Id
    private long cart_id;
    private User user;
    private int itemsCount;
    private int totalSum;
    private Map<Product, Integer> products;

    public Cart() {
        itemsCount = 0;
        totalSum = 0;
        products = new HashMap<>();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cart_id=" + cart_id +
                ", user_id=" + user.getId() +
                ", itemsCount=" + itemsCount +
                ", totalSum=" + totalSum +
                ", products=" + Arrays.asList(products) + "}";
    }
}
