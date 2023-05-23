package com.example.clothingapp.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class CheckoutDto {
  private String deliveryMethod;
  private BigInteger deliveryFee;
  private BigInteger totalOrderAmount;
}
