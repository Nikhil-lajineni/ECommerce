package com.example.springboot.controller;

import com.example.springboot.Models.Products;
import com.example.springboot.Service.FakeStoreProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private FakeStoreProductService fakeStoreProductService;
    @Autowired
    public ProductController(FakeStoreProductService fakeStoreProductService){
        this.fakeStoreProductService=fakeStoreProductService;
    }
    @GetMapping(value = "/products")
    public List<Products> getAllProducts(){
        return new ArrayList<>();
    }
    @GetMapping(value = "/get/{id}")
    public Products getProduct(@PathVariable Long id){
        return fakeStoreProductService.getProduct(id);
    }
    @PostMapping(value = "/add")
    public Products addNewProduct(@RequestBody Products product){
        Products product2=new Products();
        product2.setTitle("a new product");
        return product2;
    }
    @PatchMapping(value = "/{id}")
    public Products updateProduct(@PathVariable Long id,@RequestBody Products product){
        return new Products();

    }
    @PutMapping(value = "/{id}")
    public Products replaceProduct(@PathVariable Long id,@RequestBody Products products){
        return new Products();
    }
    @DeleteMapping (value = "/{id}")
    public Products deleteProduct(@PathVariable Long id){
        return new Products();
    }
}
