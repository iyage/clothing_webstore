package com.example.clothingapp.controllers;


import com.example.clothingapp.dto.UpdateCartItemDto;
import com.example.clothingapp.services.servicesImpl.UpdateCartItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateCartItem {
    @Autowired
    UpdateCartItemServiceImpl updateCartItemService;
    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping("/update_cart_item")
    public ResponseEntity<?> UpdateCartItem(@RequestBody UpdateCartItemDto updateCartItemDto){
        updateCartItemService.updateCart(updateCartItemDto);
        return  new ResponseEntity<Object>(HttpStatus.OK);
    }
}
