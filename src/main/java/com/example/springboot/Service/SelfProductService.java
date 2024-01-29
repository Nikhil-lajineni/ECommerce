package com.example.springboot.Service;

import com.example.springboot.Dto.ProductNotExistsException;
import com.example.springboot.Models.Category;
import com.example.springboot.Models.Product;
import com.example.springboot.Repository.CategoryRepository;
import com.example.springboot.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "selfProductService")
public class SelfProductService implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    SelfProductService(ProductRepository productRepository,CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    }
    @Override
    public Product getProduct(Long id) throws ProductNotExistsException {
        Optional<Product> productOptional=productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotExistsException("add product");
        }
        Product product=productOptional.get();
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> list=productRepository.findAll();
        return list;
    }

    @Override
    public Product addNewProduct(Product product) {
        Optional<Category> categoryOptional=categoryRepository.findByName(product.getCategory().getName());
        if(categoryOptional.isEmpty()){
            Category category=new Category();
           category= categoryRepository.save(product.getCategory());
            product.setCategory(category);
        }else {
           product.setCategory(categoryOptional.get());
        }
       return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotExistsException {
        Optional<Product> productOptional=productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotExistsException("no product with this id");
        }
            Product newProduct=productOptional.get();
            if(newProduct.getTitle()!=null){
                newProduct.setTitle(product.getTitle());
            }
            if(newProduct.getDescription()!=null){
                newProduct.setDescription(product.getDescription());
            }
            if(newProduct.getPrice()!=null){
                newProduct.setPrice(product.getPrice());
            }

        return productRepository.save(newProduct);

    }

    @Override
    public Product deleteProduct(Long id) throws ProductNotExistsException {
        return null;
    }
}
