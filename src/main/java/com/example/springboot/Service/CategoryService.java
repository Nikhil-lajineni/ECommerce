package com.example.springboot.Service;

import com.example.springboot.Models.Category;
import com.example.springboot.Models.Product;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();
    public List<Product> getInCategory(String categoryType);
}
