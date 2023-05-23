package com.example.clothingapp.services;


import com.example.clothingapp.dto.CheckoutDto;
import com.example.clothingapp.dto.ProcessPaymentDTO;
import org.springframework.stereotype.Service;

@Service
public interface ProcessPaymentService {
    ProcessPaymentDTO processPayment(CheckoutDto checkoutDto);
}
