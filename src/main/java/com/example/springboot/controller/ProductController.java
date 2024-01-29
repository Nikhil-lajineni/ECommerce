package com.example.springboot.controller;

import com.example.springboot.Dto.ProductNotExistsException;
import com.example.springboot.Models.Product;
import com.example.springboot.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;
    @Autowired
    public ProductController(@Qualifier(value = "selfProductService") ProductService productService){
        this.productService = productService;
    }
    @GetMapping(value = "/get/{id}")
    public Product getProduct(@PathVariable Long id) throws ProductNotExistsException {
            return productService.getProduct(id);
    }
    @GetMapping(value = "/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @PostMapping(value = "/add")
    public ResponseEntity<Product> addNewProduct(@RequestBody Product products){
        return new ResponseEntity<>(productService.addNewProduct(products), HttpStatus.OK);
    }
    @PatchMapping(value = "/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) throws ProductNotExistsException {
        return new ResponseEntity<>(productService.updateProduct(id,product),HttpStatus.OK);

    }
    @PutMapping(value = "/replace/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable Long id, @RequestBody Product products){
        return new ResponseEntity<>(productService.replaceProduct(id,products),HttpStatus.OK);
    }
    @DeleteMapping (value = "/delete/{id}")
    public Product deleteProduct(@PathVariable Long id) throws ProductNotExistsException {
        return productService.deleteProduct(id);
    }
}
