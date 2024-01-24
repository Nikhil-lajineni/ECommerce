package com.example.springboot.ControllerAdvice;

import com.example.springboot.Dto.ArthematicExceptionDto;
import com.example.springboot.Dto.ExceptionDto;
import com.example.springboot.Dto.ProductNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ArthematicExceptionDto> arthematicException(){
        ArthematicExceptionDto dto=new ArthematicExceptionDto();
        dto.setMsg("not exists" );
        return new ResponseEntity<>(dto,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ProductNotExistsException.class)
    public ResponseEntity<ExceptionDto> productNotExistsExceptionResponseEntity(
            ProductNotExistsException productNotExistsException
    ){
        ExceptionDto dto=new ExceptionDto();
        dto.setMsg(productNotExistsException.getMessage());
        dto.setDetail("check the product");
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
