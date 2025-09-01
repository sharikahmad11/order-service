package com.sharik.order_service.controller;

import com.sharik.order_service.dto.ProductDto;
import com.sharik.order_service.entity.Product;
import com.sharik.order_service.service.ProductService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@NoArgsConstructor
public class ProductController {
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) throws Exception {
        return productService.get(id);
    }

    @PostMapping("/save")
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/update")
    public Product update(@RequestBody Product product) {
        return productService.update(product);
    }

    @PatchMapping("update/{id}")
    public Product updateById(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return productService.updateById(id, productDto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return productService.delete(id);
    }

    @PostMapping("/saveAll")
    public String saveAll(@RequestBody List<Product> productList){
        return productService.saveProducts(productList);
    }
}
