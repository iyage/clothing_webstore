package com.example.clothingapp.services.servicesImpl;


import com.example.clothingapp.exceptions.ResourceNotFoundExeception;
import com.example.clothingapp.models.Cart;
import com.example.clothingapp.models.CartItem;
import com.example.clothingapp.models.Product;
import com.example.clothingapp.models.User;
import com.example.clothingapp.repositories.CartIemRepository;
import com.example.clothingapp.repositories.CartRepository;
import com.example.clothingapp.repositories.ProductRepository;
import com.example.clothingapp.repositories.UserRepository;
import com.example.clothingapp.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
   private CartIemRepository cartIemRepository;
    @Autowired
  private CartRepository cartRepository;
    @Autowired
   private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Cart findById(Long id) {
        return  cartRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundExeception("cart with id:" +id +"  does not exit"));
    }

    @Override
    public void addToCart(Long productId, String userEmail) {
        Product product = productRepository.findById(productId).get();
        User user = userRepository.findUserByEmail(userEmail).get();
        Optional<Cart> optionalCart = cartRepository.findCartByUserModel(user);
        if (optionalCart.isEmpty()) {
            Cart cart = new Cart();
            cart.setUserModel(user);
            cartRepository.save(cart);
            CartItem cartItem = new CartItem();
            cartItem.setPrice(product.getPrice());
            cartItem.setQuantity(1);
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartIemRepository.save(cartItem);
            List<CartItem> cartItemList = new ArrayList<>() {{add(cartItem);}};
            cart.setCartItemList(cartItemList);
            cartRepository.save(cart);
        } else {
            Cart cart = optionalCart.get();
            Optional<CartItem> optionalCartItem = cartIemRepository.findCartItemByCartAndProduct(cart, product);
            if (optionalCartItem.isEmpty()) {
                CartItem newCartItem = new CartItem();
                newCartItem.setCart(cart);
                newCartItem.setPrice(product.getPrice());
                newCartItem.setQuantity(1);
                newCartItem.setProduct(product);
                cartIemRepository.save(newCartItem);
                List<CartItem> cartItemList = new ArrayList<>() {{
                    add(newCartItem);
                }};
                cart.setCartItemList(cartItemList);
                cartRepository.save(cart);
            } else {
                List<CartItem> cartItems = cart.getCartItemList();
                for (CartItem e : cartItems) {
                    if (e.getProduct().getId().equals(productId)) {
                        e.setQuantity(e.getQuantity() + 1);
                    }
            }
                cart.setCartItemList(cartItems);
                cartRepository.save(cart);
                }
            }
        }

    @Override
    public Optional<Cart> findCartByUser(User user) {
        return Optional.empty();
    }
}
