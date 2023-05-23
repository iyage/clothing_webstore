
    package com.example.clothingapp.services;

    import com.example.clothingapp.dto.ProductFavouritesDto;
    import com.example.clothingapp.models.Product;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    public interface FavouriteService {
       boolean findFavouritesByProductsAndUserModel(String email, Long id);
        String findFavouritesByProductsAndUserModel(String email, ProductFavouritesDto productFavouritesDto);
        Product viewProductFromFavourite(Long id, String email);
        List<Product> viewAllProductFromFavourite(String email);
    }
