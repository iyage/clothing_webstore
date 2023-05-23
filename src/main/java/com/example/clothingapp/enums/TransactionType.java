package com.example.clothingapp.enums;

public enum TransactionType {
    FUNDWALLET("fundwallet"),MAKEPAYMENT("makepayment"), WITHDRAWAL("withdrawal");
   private final String transaction ;

    TransactionType(String transaction) {
        this.transaction = transaction;
    }
}
