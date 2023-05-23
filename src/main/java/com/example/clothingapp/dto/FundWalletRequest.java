package com.example.clothingapp.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class FundWalletRequest {
    private Double amount;
    private Date transactionDate;
    private String email;
}
