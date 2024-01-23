package com.example.springboot.controller;

import com.example.springboot.Models.Products;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @GetMapping(value = "/products")
    public List<Products> getAllProducts(){
//        RestTemplate restTemplate=new RestTemplate().getForObject("https://fakestoreapi.com/products",
//                )
        return new ArrayList<>();
    }
    @GetMapping(value = "/get/{id}")
    public Products getProduct(@PathVariable Long id){
        return new Products();
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
