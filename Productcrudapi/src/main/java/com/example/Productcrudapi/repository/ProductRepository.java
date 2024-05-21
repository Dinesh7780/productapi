package com.example.Productcrudapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Productcrudapi.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
