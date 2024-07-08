package com.example.products.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.products.entity.Product;
import com.example.products.entity.ProductUpdateRequest;
import com.example.products.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product updateProduct(int id, ProductUpdateRequest product) {
		Optional<Product> productDb = this.productRepository.findById(id);

		if (productDb.isPresent()) {
			Product productUpdate = productDb.get();
			productUpdate.setPrice(product.getPrice());
			productRepository.save(productUpdate);
			return productUpdate;
		} else {
			System.out.print("Record not found with id : " + id);
		}
		return null;
	}

	@Override
	public List<Product> getAllProducts() {
		return this.productRepository.findAll();
	}

}
