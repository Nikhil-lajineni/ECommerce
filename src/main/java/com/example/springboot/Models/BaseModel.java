package com.example.springboot.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@MappedSuperclass
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) ->to create table for parent table also
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private Boolean isDeleted;
}
