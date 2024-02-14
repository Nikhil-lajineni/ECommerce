package com.example.springboot.controller;

import com.example.springboot.Commons.AuthenticationCommons;
import com.example.springboot.Dto.ProductNotExistsException;
import com.example.springboot.Dto.Role;
import com.example.springboot.Dto.UserDto;
import com.example.springboot.Models.Product;
import com.example.springboot.Service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;
    private AuthenticationCommons authenticationCommons;

    public ProductController(@Qualifier(value = "fakeStoreService") ProductService productService,
                             AuthenticationCommons authenticationCommons){
        this.productService = productService;
        this.authenticationCommons=authenticationCommons;
    }
    @GetMapping(value = "/get/{id}")
    public Product getProduct(@PathVariable Long id) throws ProductNotExistsException {
            return productService.getProduct(id);
    }
    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> getAllProducts(@RequestHeader("AuthorizationToken") String token) {
        //
        UserDto userDto = authenticationCommons.validateToken(token);

        if (userDto == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        boolean isAdmin = false;

        for (Role role : userDto.getRoles()) {
            if (role.getName().equals("ADMIN")) {
                isAdmin = true;
                break;
            }
        }

        if (!isAdmin) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        List<Product> products = productService.getAllProducts(); // o p q

        List<Product> finalProducts = new ArrayList<>();

        for (Product p : products) { // o  p q
            p.setTitle("Hello" + p.getTitle());
            finalProducts.add(p);
        }

        ResponseEntity<List<Product>> response = new ResponseEntity<>(
                finalProducts, HttpStatus.FORBIDDEN
        );
        return response;
    }
    @PostMapping(value = "/add")
    public ResponseEntity<Product> addNewProduct(@RequestBody Product products) throws JsonProcessingException {
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
