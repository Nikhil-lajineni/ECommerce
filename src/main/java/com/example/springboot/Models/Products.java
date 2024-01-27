package com.example.springboot.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Products extends BaseModel {

    private String title;
    private Double price;
    @ManyToOne
    private Category category;
    private String description;
    private String image;
}
