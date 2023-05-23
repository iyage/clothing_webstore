package com.example.clothingapp.services.servicesImpl;


import com.example.clothingapp.dto.UpdateCartItemDto;
import com.example.clothingapp.models.CartItem;
import com.example.clothingapp.repositories.CartIemRepository;
import com.example.clothingapp.services.UpdateCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCartItemServiceImpl implements UpdateCartItemService {
   @Autowired
   private CartIemRepository cartIemRepository;
    @Override
    public void updateCart(UpdateCartItemDto updateCartItemDto) {
        Long cartId = updateCartItemDto.getCartItemId();
        int cartItemQuanty = updateCartItemDto.getItemQuanty();
        CartItem cartItem = cartIemRepository.findById(cartId).get();
        cartItem.setQuantity(cartItemQuanty);
        cartIemRepository.save(cartItem);
    }
}
