package com.example.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.products.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
