package com.example.springboot.controller;

import com.example.springboot.Models.Category;
import com.example.springboot.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;
    @Autowired
    CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }
    @GetMapping(value = "/category/getAll")
    public ResponseEntity<List<Category>> getAllCategory(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }
    @GetMapping(value = "category/get")
    public ResponseEntity<List<Category>> getInCategory(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

}
