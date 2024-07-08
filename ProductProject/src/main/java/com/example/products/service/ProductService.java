package com.example.products.service;

import java.util.List;

import com.example.products.entity.Product;
import com.example.products.entity.ProductUpdateRequest;

public interface ProductService {

	Product updateProduct(int id, ProductUpdateRequest product);
	List<Product> getAllProducts();
}
