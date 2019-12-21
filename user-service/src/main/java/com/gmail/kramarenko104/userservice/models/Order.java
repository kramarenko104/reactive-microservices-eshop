package com.gmail.kramarenko104.userservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Data
public class Order implements Serializable {

    @Id
    private long order_id;
    private long order_number;
    private User user;
    private String status;
    private int itemsCount;
    private int totalSum;
    private Map<Product, Integer> products;

    public Order() {
        itemsCount = 0;
        totalSum = 0;
        products = new HashMap<>();
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_number=" + order_number +
                ", user=" + user +
                ", itemsCount=" + itemsCount +
                ", totalSum=" + totalSum +
                ", products=" + Arrays.asList(products) + "}";
    }
}
