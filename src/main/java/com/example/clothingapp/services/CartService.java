package com.example.clothingapp.services;


import com.example.clothingapp.models.Cart;
import com.example.clothingapp.models.User;

import java.util.Optional;

public interface CartService {
 Cart findById(Long id);
 void addToCart(Long productId, String userEmail);
 Optional<Cart> findCartByUser(User user);
}
