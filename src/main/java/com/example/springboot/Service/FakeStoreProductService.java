package com.example.springboot.Service;

import com.example.springboot.Dto.FakeStoreDto;
import com.example.springboot.Models.Category;
import com.example.springboot.Models.Products;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.resource.HttpResource;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

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

    public Products ConvertDtoToProduct(FakeStoreDto productDto) {
        Products product=new Products();
        ;
        BeanUtils.copyProperties(productDto,product);
        return product;
    }
    public List<Products> getAllProducts() {



//        List<FakeStoreProductDto> response = restTemplate.getForObject(
//                "https://fakestoreapi.com/products",
//                List<FakeStoreProductDto>.class
//        );

        // runtime
        FakeStoreDto[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreDto[].class
        );


        List<Products> answer = new ArrayList<>();


        for (FakeStoreDto dto: response) {
            Products p=new Products();
            BeanUtils.copyProperties(dto,p);
            answer.add(p);
        }

        return answer;
    }
    public Products addNewProduct(FakeStoreDto fakeStoreDto){
        FakeStoreDto fakeStoreDto2 = restTemplate.postForObject("https://fakestoreapi.com/products",
                0, FakeStoreDto.class);
        Products products=new Products();
        products.setId(fakeStoreDto2.getId());
        products.setTitle(fakeStoreDto2.getTitle());
        products.setPrice(fakeStoreDto2.getPrice());
        Category category=new Category();
        category.setId(1L);
        category.setName(fakeStoreDto2.getCategory());
        products.setDescription(fakeStoreDto2.getDescription());
        products.setImage(fakeStoreDto2.getImage());

        return products;

    }
    public Products replaceProduct(Long id, Products product) {
        FakeStoreDto fakeStoreProductDto = new FakeStoreDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getDescription());
        fakeStoreProductDto.setImage(product.getImage());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreDto.class);
        HttpMessageConverterExtractor<FakeStoreDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreDto.class, restTemplate.getMessageConverters());
        FakeStoreDto response = restTemplate.execute("https://fakestoreapi.com/products/" + id,
                HttpMethod.PUT, requestCallback, responseExtractor);
        Products p=new Products();
        BeanUtils.copyProperties(response,p);
        return p;
    }
    public Products deleteProduct(Long id){
        Products p=getProduct(id);
        restTemplate.delete("https://fakestoreapi.com/products/6",FakeStoreDto.class);
        return p;

    }

}
