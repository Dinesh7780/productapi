package com.example.Productcrudapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.Productcrudapi.model.Product;
import com.example.Productcrudapi.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void getAllProducts_ReturnsAllProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Product 1", "Description 1", BigDecimal.TEN, null, null, null));
        products.add(new Product(2L, "Product 2", "Description 2", BigDecimal.valueOf(20), null, null, null));
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertEquals(2, result.size());
    }

    @Test
    public void getProductById_ReturnsProductIfExists() {
        Product product = new Product(1L, "Product 1", "Description 1", BigDecimal.TEN, null, null, null);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getProductById(1L);

        assertEquals(product, result.orElse(null));
    }

    @Test
    public void createProduct_CreatesProduct() {
        Product product = new Product(1L, "Product 1", "Description 1", BigDecimal.TEN, null, null, null);
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.createProduct(product);

        assertEquals(product, result);
    }

    @Test
    public void deleteProduct_DeletesProduct() {
        doNothing().when(productRepository).deleteById(1L);

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    public void updateProduct_UpdatesProductIfExists() {
        Product existingProduct = new Product(1L, "Product 1", "Description 1", BigDecimal.TEN, null, null, null);
        Product updatedProduct = new Product(1L, "Updated Product", "Updated Description", BigDecimal.valueOf(20), null, null, null);
        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(updatedProduct);

        Product result = productService.updateProduct(1L, updatedProduct);

        assertEquals(updatedProduct, result);
    }
}
