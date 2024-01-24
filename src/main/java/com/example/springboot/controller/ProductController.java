package com.example.springboot.controller;

import com.example.springboot.Dto.FakeStoreDto;
import com.example.springboot.Dto.ProductNotExistsException;
import com.example.springboot.Models.Products;
import com.example.springboot.Service.FakeStoreProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

    private FakeStoreProductService fakeStoreProductService;
    @Autowired
    public ProductController(FakeStoreProductService fakeStoreProductService){
        this.fakeStoreProductService=fakeStoreProductService;
    }
    @GetMapping(value = "/get/{id}")
    public Products getProduct(@PathVariable Long id) throws ProductNotExistsException {

            return fakeStoreProductService.getProduct(id);
    }
    @GetMapping(value = "/products")
    public List<Products> getAllProducts(){
        return fakeStoreProductService.getAllProducts();
    }
    @PostMapping(value = "/add")
    public ResponseEntity<Products> addNewProduct(@RequestBody Products products){
        return new ResponseEntity<>(fakeStoreProductService.addNewProduct(products), HttpStatus.OK);
    }
    @PatchMapping(value = "/{id}")
    public Products updateProduct(@PathVariable Long id,@RequestBody Products product){
        return fakeStoreProductService.replaceProduct(id,product);

    }
    @PutMapping(value = "/{id}")
    public Products replaceProduct(@PathVariable Long id,@RequestBody Products products){
        return fakeStoreProductService.replaceProduct(id,products);
    }
    @DeleteMapping (value = "/delete/{id}")
    public Products deleteProduct(@PathVariable Long id) throws ProductNotExistsException {
        return fakeStoreProductService.deleteProduct(id);
    }
}
