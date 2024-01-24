package com.example.springboot.Dto;

public class ProductNotExistsException extends Exception{
    public ProductNotExistsException(String msg){
        super(msg);
    }
}
