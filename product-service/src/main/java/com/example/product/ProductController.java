package com.example.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @PostMapping
    public Product addNewProduct(
            @RequestBody Product product
    ) {
        return productRepo.save(product);
    }

}
