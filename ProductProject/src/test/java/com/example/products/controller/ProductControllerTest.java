package com.example.products.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.products.entity.Product;
import com.example.products.entity.ProductUpdateRequest;
import com.example.products.service.ProductService;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

	@Mock
	ProductService productService;

	@InjectMocks
	ProductController productController;

	@Test
	void getAllProductsTest_Pass() {
		Product product = new Product();
		product.setId(1);
		product.setName("X");
		product.setPrice(500);
		List<Product> products = new ArrayList<Product>();
		products.add(product);
		when(productService.getAllProducts()).thenReturn(products);

		ResponseEntity<List<Product>> response = productController.getAllProduct();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(products, response.getBody());
	}

	@Test
	void getAllProductsTest_Fail() {

		when(productService.getAllProducts()).thenThrow(RuntimeException.class);

		assertThrows(RuntimeException.class, () -> productController.getAllProduct());
	}

	@Test
	void updateProductTest_Pass() {
		Product product = new Product();
		product.setId(1);
		product.setName("X");
		product.setPrice(500);

		ProductUpdateRequest productUpdateRequest = new ProductUpdateRequest();
		productUpdateRequest.setPrice(500);

		when(productService.updateProduct(1, productUpdateRequest)).thenReturn(product);

		ResponseEntity<Product> response = productController.updateProduct(1, productUpdateRequest);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(product, response.getBody());
	}

	@Test
	void updateProductTest_Fail() {

		when(productService.updateProduct(1, null)).thenThrow(RuntimeException.class);

		assertThrows(RuntimeException.class, () -> productController.updateProduct(1, null));
	}

}
