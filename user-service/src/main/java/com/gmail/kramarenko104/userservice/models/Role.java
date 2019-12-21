package com.gmail.kramarenko104.userservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import java.io.Serializable;

@NoArgsConstructor
@Data
public class Role implements Serializable {

    @Id
    private long role_id;
    private String name;

    public Role(String name){
       this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}