package com.example.Productcrudapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.Productcrudapi.model.Category;
import com.example.Productcrudapi.service.CategoryService;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {
    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Test
    public void getAllCategories_ReturnsAllCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "Category 1", "Description 1", null));
        categories.add(new Category(2L, "Category 2", "Description 2", null));
        when(categoryService.getAllCategories()).thenReturn(categories);

        List<Category> result = categoryController.getAllCategories();

        assertEquals(2, result.size());
    }

    @Test
    public void getCategoryById_ReturnsCategoryIfExists() {
        Category category = new Category(1L, "Category 1", "Description 1", null);
        when(categoryService.getCategoryById(1L)).thenReturn(Optional.of(category));

        ResponseEntity<Category> response = categoryController.getCategoryById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(category, response.getBody());
    }

    @Test
    public void getCategoryById_ReturnsNotFoundIfCategoryDoesNotExist() {
        when(categoryService.getCategoryById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Category> response = categoryController.getCategoryById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    
}
