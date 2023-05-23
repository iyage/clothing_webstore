package com.example.clothingapp.controllers;

import com.example.clothingapp.services.servicesImpl.CartItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController

public class RemoveItemFromCart {
    @Autowired
    private CartItemServiceImpl cartItemService;
    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/remove_item_from_cart/{id}")
    public ResponseEntity<?>removeItemsFromCart(@PathVariable Long id, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        cartItemService.removeItemFromCart(id,email);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
