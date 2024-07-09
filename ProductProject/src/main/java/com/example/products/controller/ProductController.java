package com.example.products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.products.entity.Product;
import com.example.products.entity.ProductUpdateRequest;
import com.example.products.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity < List < Product >> getAllProduct() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }
    
    @PutMapping("/products/{id}")
    public ResponseEntity < Product > updateProduct(@PathVariable int id, @RequestBody ProductUpdateRequest product) {
        return ResponseEntity.ok().body(productService.updateProduct(id,product));
    }

}
