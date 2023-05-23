package com.example.clothingapp.repositories;


import com.example.clothingapp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByProductNameContaining(String keyword);
    Optional<Product> findProductsByColorAndProductName(String color, String productName);
    Optional<Product> findProductByProductName(String productName);
}
