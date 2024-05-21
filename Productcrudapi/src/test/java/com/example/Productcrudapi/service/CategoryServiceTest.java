package com.example.Productcrudapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.example.Productcrudapi.model.Category;
import com.example.Productcrudapi.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void getAllCategories_ReturnsAllCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "Category 1", "Description 1", null));
        categories.add(new Category(2L, "Category 2", "Description 2", null));
        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> result = categoryService.getAllCategories();

        assertEquals(2, result.size());
    }

    @Test
    public void getCategoryById_ReturnsCategoryIfExists() {
        Category category = new Category(1L, "Category 1", "Description 1", null);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Optional<Category> result = categoryService.getCategoryById(1L);

        assertEquals(category, result.orElse(null));
    }

    @Test
    public void createCategory_CreatesCategory() {
        Category category = new Category(1L, "Category 1", "Description 1", null);
        when(categoryRepository.save(category)).thenReturn(category);

        Category result = categoryService.createCategory(category);

        assertEquals(category, result);
    }

    @Test
    public void deleteCategory_DeletesCategory() {
        doNothing().when(categoryRepository).deleteById(1L);

        categoryService.deleteCategory(1L);

        verify(categoryRepository, times(1)).deleteById(1L);
    }

    @Test
    public void updateCategory_UpdatesCategoryIfExists() {
        Category existingCategory = new Category(1L, "Category 1", "Description 1", null);
        Category updatedCategory = new Category(1L, "Updated Category", "Updated Description", null);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.save(existingCategory)).thenReturn(updatedCategory);

        Category result = categoryService.updateCategory(1L, updatedCategory);

        assertEquals(updatedCategory, result);
    }
}
