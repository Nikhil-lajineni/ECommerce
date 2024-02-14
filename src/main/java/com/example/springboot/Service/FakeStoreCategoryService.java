package com.example.springboot.Service;

import com.example.springboot.Dto.CategoryDto;
import com.example.springboot.Models.Category;
import com.example.springboot.Models.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class FakeStoreCategoryService implements CategoryService{
    private RestTemplate restTemplate;

    FakeStoreCategoryService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    @Override
    public List<Category> getAllCategories() {
        CategoryDto[] categories=restTemplate.
                getForObject("https://fakestoreapi.com/products/categories"
        ,CategoryDto[].class);
        ArrayList<Category> list=new ArrayList<>();
        for(CategoryDto c: categories){
            Category category=new Category();
            category.setName(c.getName());
            list.add(category);
        }


        return list;
    }
    @Override
    public List<Product> getInCategory(String category) {
       Product p[]= restTemplate.getForObject("https://fakestoreapi.com/products/category/jewelery",
                Product[].class);
       ArrayList<Product> list=new ArrayList<>();
       for(Product product:p){
           Product product1=new Product();
           BeanUtils.copyProperties(product,product1);
           list.add(product1);
       }
        return list;
    }
}
