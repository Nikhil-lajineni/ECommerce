package com.example.springboot.Service;

import com.example.springboot.Dto.ProductNotExistsException;
import com.example.springboot.Models.Products;

import java.util.List;

public interface FakeStoreService {
    public Products getProduct(Long id) throws ProductNotExistsException;
    public List<Products> getAllProducts();
    public Products addNewProduct(Products product);
    public Products replaceProduct(Long id, Products product);
    public Products deleteProduct(Long id) throws ProductNotExistsException;
}
