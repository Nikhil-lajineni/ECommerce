package com.example.springboot.SingleTable;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "single_table")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "userType",
        discriminatorType = DiscriminatorType.INTEGER
)
@DiscriminatorValue("0")
public class User {
    @Id
    private Long Id;
    private String name;
    private String email;
}
