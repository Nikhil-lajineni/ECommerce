package com.example.springboot.Models;

import lombok.Data;

@Data
public class Products {
    private Long id;
    private String title;
    private Double price;
    private Category Category;
    private String description;
    private String image;
}
