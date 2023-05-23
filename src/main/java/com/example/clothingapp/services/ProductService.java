package com.example.clothingapp.services;



import com.example.clothingapp.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> adminViewAllProductsPaginated(int page, int size);
    Product adminFetchParticularProduct(Long id);
    List<Product> searchProductsByKeyword(String keyword);
}
