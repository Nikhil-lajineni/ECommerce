package com.example.springboot.Service;

import com.example.springboot.Dto.FakeStoreDto;
import com.example.springboot.Models.Products;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService {
    private RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    public Products getProduct(Long id){
        FakeStoreDto productDto = restTemplate.getForObject("https://fakestoreapi.com/products/1",
                FakeStoreDto.class);
        return ConvertDtoToProduct(productDto);
    }

    private Products ConvertDtoToProduct(FakeStoreDto productDto) {
        Products product=new Products();
        ;
        BeanUtils.copyProperties(productDto,product);
        return product;
    }

}
