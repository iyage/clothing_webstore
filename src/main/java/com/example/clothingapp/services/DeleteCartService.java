package com.example.clothingapp.services;


import com.example.clothingapp.models.Cart;
import org.springframework.stereotype.Service;

@Service
public interface DeleteCartService {
    void emptyCart(Cart cart);
}
