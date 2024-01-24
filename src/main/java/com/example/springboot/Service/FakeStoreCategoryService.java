package com.example.springboot.Service;

import com.example.springboot.Dto.FakeStoreDto;
import com.example.springboot.Models.Category;
import com.example.springboot.Models.Products;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
        return new ArrayList<>();

    }

    @Override
    public List<Category> getInCategory() {
        return new ArrayList<>();
    }
}
