package com.example.clothingapp.repositories;


import com.example.clothingapp.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository <ProductCategory, Long> {
    Optional<ProductCategory> findProductCategoryByProductCategoryName(String categoryName);

    ProductCategory findByProductCategoryName(String categoryName);
}
