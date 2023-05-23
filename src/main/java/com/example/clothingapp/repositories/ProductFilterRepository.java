package com.example.clothingapp.repositories;



import com.example.clothingapp.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductFilterRepository extends CrudRepository<Product, Long> {

}
