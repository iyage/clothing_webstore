package com.example.clothingapp.services.servicesImpl;


import com.example.clothingapp.models.Cart;
import com.example.clothingapp.models.CartItem;
import com.example.clothingapp.models.User;
import com.example.clothingapp.repositories.CartIemRepository;
import com.example.clothingapp.repositories.CartRepository;
import com.example.clothingapp.repositories.UserRepository;
import com.example.clothingapp.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartIemRepository cartIemRepository;
    @Autowired
   private CartRepository cartRepository;
    @Autowired
   private UserRepository userRepository;
    @Override
    public void removeItemFromCart(Long id, String email) {
        User user = userRepository.findUserByEmail(email).get();
        Cart cart = cartRepository.findCartByUserModel(user).get();
        List<CartItem> cartItems = cart.getCartItemList();

        CartItem cartItem = new CartItem();
        for (CartItem item : cartItems) {
            if(item.getId().equals(id)){
              cartItem =item;
            }
        }
        cartItems.remove(cartItem);
        cartIemRepository.delete(cartItem);

    }
}
