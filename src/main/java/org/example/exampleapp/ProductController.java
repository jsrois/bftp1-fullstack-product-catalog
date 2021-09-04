package org.example.exampleapp;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> allProducts() {
        return productRepository.findAll();
    }

    @PostMapping("/products")
    public ResponseEntity<String> createProduct(@RequestBody Product product) {

        productRepository.save(product);

        return ResponseEntity.ok("product created successfully");
    }
}
