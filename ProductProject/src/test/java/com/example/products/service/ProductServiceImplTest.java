package com.example.products.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.products.entity.Product;
import com.example.products.entity.ProductUpdateRequest;
import com.example.products.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
	@Mock
	ProductRepository productRepository;

	@InjectMocks
	ProductServiceImpl productService;

	@Test
	void getAllProductsTest_Pass() {
		Product product = new Product();
		product.setId(1);
		product.setName("X");
		product.setPrice(500);
		List<Product> products = new ArrayList<Product>();
		products.add(product);

		when(productRepository.findAll()).thenReturn(products);

		List<Product> response = productService.getAllProducts();
		assertEquals(products, response);
	}

	@Test
	void getAllProductsTest_Fail() {

		when(productRepository.findAll()).thenThrow(RuntimeException.class);

		assertThrows(RuntimeException.class, () -> productRepository.findAll());
	}

	@Test
	void updateProductTest_Pass() {
		Product product = new Product();
		product.setId(1);
		product.setName("X");
		product.setPrice(500);

		ProductUpdateRequest productUpdateRequest = new ProductUpdateRequest();
		productUpdateRequest.setPrice(500);

		when(productRepository.findById(1)).thenReturn(Optional.of(product));
		when(productRepository.save(product)).thenReturn(product);

		Product response = productService.updateProduct(1, productUpdateRequest);

		assertEquals(product, response);
	}

	@Test
	void updateProductTest_Fail() {

		when(productRepository.findById(1)).thenReturn(Optional.empty());

		assertEquals(null, productService.updateProduct(1, null));
	}

}
