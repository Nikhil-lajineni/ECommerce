package com.example.springboot.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
public class CategoryDto {
    CategoryDto(){

    }
    CategoryDto(String name){
        this.name=name;
    }

    private String name;
}
