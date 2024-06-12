package com.ahmed.springrest.myproductsrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahmed.springrest.myproductsrestapi.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
