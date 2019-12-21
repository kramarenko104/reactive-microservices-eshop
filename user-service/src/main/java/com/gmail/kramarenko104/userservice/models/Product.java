package com.gmail.kramarenko104.userservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class Product implements Serializable {

    @Id
    private long product_id;
    private String name;
    private int category;
    private int price;
    private String description;
    private String image;

    @Override
    public String toString() {
        return "{product_id:" + product_id + ",name:" + name + ",price:" + price + "}";
    }
}
