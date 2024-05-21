package com.example.Productcrudapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Productcrudapi.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}