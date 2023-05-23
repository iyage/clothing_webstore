package com.example.clothingapp.repositories;


import com.example.clothingapp.models.Cart;
import com.example.clothingapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findCartByUserModel(User user);
}
