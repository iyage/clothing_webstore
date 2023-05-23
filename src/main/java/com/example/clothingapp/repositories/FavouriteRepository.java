
package com.example.clothingapp.repositories;


import com.example.clothingapp.models.Favourites;
import com.example.clothingapp.models.Product;
import com.example.clothingapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourites, Long> {
    Optional<Favourites> findFavouritesByProductsAndUserModel(Product product, User user);
    Favourites findFavouritesByUserModelEmail(String email);

}
