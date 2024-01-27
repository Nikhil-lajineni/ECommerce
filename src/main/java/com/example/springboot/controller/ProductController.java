package com.example.springboot.controller;

import com.example.springboot.Dto.FakeStoreDto;
import com.example.springboot.Dto.ProductNotExistsException;
import com.example.springboot.Models.Products;
import com.example.springboot.Service.FakeStoreProductService;
import com.example.springboot.Service.FakeStoreService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private FakeStoreService fakeStoreService;
    @Autowired
    public ProductController(@Qualifier(value = "selfProductService") FakeStoreService fakeStoreService){
        this.fakeStoreService=fakeStoreService;
    }
    @GetMapping(value = "/get/{id}")
    public Products getProduct(@PathVariable Long id) throws ProductNotExistsException {
            return fakeStoreService.getProduct(id);
    }
    @GetMapping(value = "/products")
    public List<Products> getAllProducts(){
        return fakeStoreService.getAllProducts();
    }
    @PostMapping(value = "/add")
    public ResponseEntity<Products> addNewProduct(@RequestBody Products products){
        return new ResponseEntity<>(fakeStoreService.addNewProduct(products), HttpStatus.OK);
    }
    @PatchMapping(value = "/{id}")
    public Products updateProduct(@PathVariable Long id,@RequestBody Products product){
        return fakeStoreService.replaceProduct(id,product);

    }
    @PutMapping(value = "/{id}")
    public Products replaceProduct(@PathVariable Long id,@RequestBody Products products){
        return fakeStoreService.replaceProduct(id,products);
    }
    @DeleteMapping (value = "/delete/{id}")
    public Products deleteProduct(@PathVariable Long id) throws ProductNotExistsException {
        return fakeStoreService.deleteProduct(id);
    }
}
