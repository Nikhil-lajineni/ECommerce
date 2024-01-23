package com.example.springboot.Dto;

import com.example.springboot.Models.Category;
import lombok.Data;

@Data
public class FakeStoreDto {
    private Long id;
    private String title;
    private Double price;
    private String category;
    private String description;
    private String image;

}
