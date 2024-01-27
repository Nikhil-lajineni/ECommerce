package com.example.springboot.JoinedTable;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "jt_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    private Long id;
    private String name;
    private String email;

}
