package com.example.clothingapp.services.servicesImpl;


import com.example.clothingapp.models.Cart;
import com.example.clothingapp.repositories.CartIemRepository;
import com.example.clothingapp.repositories.CartRepository;
import com.example.clothingapp.services.DeleteCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DeleteCartSeviceImpl implements DeleteCartService {
    @Autowired
    private CartIemRepository cartIemRepository;
    @Autowired
  private CartRepository cartRepository;
    @Override
    public void emptyCart(Cart cart) {
        cartIemRepository.deleteByCart_Id(cart.getId());
    }
}
