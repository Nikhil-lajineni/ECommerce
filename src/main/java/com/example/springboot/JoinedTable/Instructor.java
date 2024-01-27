package com.example.springboot.JoinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Entity(name = "jt_instructor")
@PrimaryKeyJoinColumn(name ="user_id")
public class Instructor extends User {
//    @Id
//    private Long id;
    private String favouriteStudent;
}
