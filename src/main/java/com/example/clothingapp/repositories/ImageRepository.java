package com.example.clothingapp.repositories;


import com.example.clothingapp.models.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ProductImages, Long> {

}
