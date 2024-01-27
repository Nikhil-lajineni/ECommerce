package com.example.springboot.Service;

import com.example.springboot.Dto.ProductNotExistsException;
import com.example.springboot.Models.Products;
import com.example.springboot.Repository.CategoryRepository;
import com.example.springboot.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "selfProductService")
public class SelfProductService implements FakeStoreService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    SelfProductService(ProductRepository productRepository,CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    }
    @Override
    public Products getProduct(Long id) throws ProductNotExistsException {
        return null;
    }

    @Override
    public List<Products> getAllProducts() {
        return null;
    }

    @Override
    public Products addNewProduct(Products product) {
        return null;
    }

    @Override
    public Products replaceProduct(Long id, Products product) {
        return null;
    }

    @Override
    public Products deleteProduct(Long id) throws ProductNotExistsException {
        return null;
    }
}
