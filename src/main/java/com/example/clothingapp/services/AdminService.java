package com.example.clothingapp.services;


import com.example.clothingapp.dto.ProductDto;
import com.example.clothingapp.models.Product;
import com.example.clothingapp.models.ProductImages;
import com.example.clothingapp.models.User;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    User createDispatchRider (User dispatchRider);

    Product createProduct (ProductDto products);

    ProductImages saveToDB(String imageUrl, Long productId);

    Product updateProduct(Long id, ProductDto products);

    void deleteProduct(Long id);
}
