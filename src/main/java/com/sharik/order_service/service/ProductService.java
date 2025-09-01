package com.sharik.order_service.service;

import com.sharik.order_service.dto.ProductDto;
import com.sharik.order_service.entity.Product;
import com.sharik.order_service.exception.ProductNotFoundException;
import com.sharik.order_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    public Product get(Long id) throws Exception {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not Found!!!"));
    }

    public Product save(Product Product) {
        return productRepository.save(Product);
    }

    public Product update(Product product) {
        if(null!=product.getProductId()) {
            Product existingProduct = productRepository.findById(product.getProductId()).orElseThrow(() -> new ProductNotFoundException("Product Not Found!!!"));
        } else{
            throw new ProductNotFoundException("Product Not Found!!!");
        }
        Product updatedProduct = new Product();
        updatedProduct.setName(product.getName());
        updatedProduct.setPrice(product.getPrice());
        return productRepository.save(updatedProduct);
    }

    public Product updateById(Long id, ProductDto productDto) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not Found!!!"));
        Product newProduct = new Product();
        if(null!=productDto.getName())
            existingProduct.setName(existingProduct.getName());
        if(0!=productDto.getPrice())
            existingProduct.setPrice(existingProduct.getPrice());

        return productRepository.save(existingProduct);
    }

    public String delete(Long id) {
        Product Product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not Found!!!"));
        if(null!=Product){
            productRepository.delete(Product);
            return "Product deleted successfully!!!";
        }
        return "Product Not Found!!!";
    }

    public String saveProducts(List<Product> ProductList) {
        Instant start = Instant.now();
        System.out.printf("Product Data addition to DB started : " + start);
        productRepository.saveAll(ProductList);
        Instant end = Instant.now();
        System.out.printf("Product Data addition to DB completed : " + end);
        System.out.printf("Total time taken: " + Duration.between(end,start));
        return "Products data added to Database";
    }
}
