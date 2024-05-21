package com.example.Productcrudapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Productcrudapi.model.Product;
import com.example.Productcrudapi.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    public void getAllProducts_ReturnsAllProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Product 1", "Description 1", BigDecimal.TEN, null, null, null));
        products.add(new Product(2L, "Product 2", "Description 2", BigDecimal.valueOf(20), null, null, null));
        when(productService.getAllProducts()).thenReturn(products);

        List<Product> result = productController.getAllProducts();

        assertEquals(2, result.size());
    }

    @Test
    public void getProductById_ReturnsProductIfExists() {
        Product product = new Product(1L, "Product 1", "Description 1", BigDecimal.TEN, null, null, null);
        when(productService.getProductById(1L)).thenReturn(Optional.of(product));

        ResponseEntity<Product> response = productController.getProductById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void getProductById_ReturnsNotFoundIfProductDoesNotExist() {
        when(productService.getProductById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Product> response = productController.getProductById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}