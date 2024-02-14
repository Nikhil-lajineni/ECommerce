package com.example.springboot.Service;

import com.example.springboot.Dto.ProductNotExistsException;
import com.example.springboot.Models.Product;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ProductService {
    public Product getProduct(Long id) throws ProductNotExistsException;
    public List<Product> getAllProducts();
    public Product addNewProduct(Product product) throws JsonProcessingException;
    public Product replaceProduct(Long id, Product product);
    public Product updateProduct(Long id, Product product) throws ProductNotExistsException;
    public Product deleteProduct(Long id) throws ProductNotExistsException;
}
