package com.example.clothingapp.repositories;

import com.example.clothingapp.models.ProductSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository<ProductSubCategory, Long> {
Optional<ProductSubCategory> findProductSubCategoryBySubCategoryName(String subCategoryName);

    ProductSubCategory findBySubCategoryName(String subCategoryName);
}
