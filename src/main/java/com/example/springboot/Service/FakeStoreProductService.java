package com.example.springboot.Service;

import com.example.springboot.Dto.FakeStoreDto;
import com.example.springboot.Dto.ProductNotExistsException;
import com.example.springboot.Models.Category;
import com.example.springboot.Models.Products;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.resource.HttpResource;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service(value = "fakeStoreService")
public class FakeStoreProductService implements FakeStoreService{
    private RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    public Products getProduct(Long id) throws ProductNotExistsException {

        FakeStoreDto productDto = restTemplate.getForObject("https://fakestoreapi.com/products/"+id,
                FakeStoreDto.class);
        if(productDto==null){
            throw new ProductNotExistsException("not found " +id+ " check id");
        }
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
    private FakeStoreDto convertProductToFakeStoreProductDTO(Products product) {
        FakeStoreDto productDTO = new FakeStoreDto();
        productDTO.setTitle(product.getTitle());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategory(product.getCategory().getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setImage(product.getImage());
        return productDTO;
    }
    public Products addNewProduct(Products product){
        FakeStoreDto productDTO = convertProductToFakeStoreProductDTO(product);
        FakeStoreDto responseDTO = restTemplate.postForObject("https://fakestoreapi.com/products", productDTO, FakeStoreDto.class);
        if (responseDTO != null)
            return convertFakeStoreProductToProduct(responseDTO);
        System.out.println("Product not created");
        return null;
    }
    private Products convertFakeStoreProductToProduct(FakeStoreDto fakeStoreProduct) {
        Products product = new Products();
        product.setTitle(fakeStoreProduct.getTitle());
        product.setId(fakeStoreProduct.getId());
        product.setPrice(fakeStoreProduct.getPrice());
        product.setDescription(fakeStoreProduct.getDescription());
        product.setImage(fakeStoreProduct.getImage());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProduct.getCategory());

        return product;
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
    public Products deleteProduct(Long id) throws ProductNotExistsException {
        Products p=getProduct(id);
        restTemplate.delete("https://fakestoreapi.com/products/6",FakeStoreDto.class);
        return p;

    }

}
