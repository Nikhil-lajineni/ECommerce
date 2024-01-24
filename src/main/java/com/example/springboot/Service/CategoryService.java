package com.example.springboot.Service;

import com.example.springboot.Models.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();
    public List<Category> getInCategory();
}
