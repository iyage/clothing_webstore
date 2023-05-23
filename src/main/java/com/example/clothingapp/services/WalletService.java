package com.example.clothingapp.services;


import com.example.clothingapp.dto.FundWalletRequest;
import com.example.clothingapp.dto.WithdrawalDto;
import com.example.clothingapp.exceptions.InsufficientFundsException;
import com.example.clothingapp.models.Wallet;
import org.springframework.stereotype.Service;

@Service
public interface WalletService {
    Wallet topUpWalletAccount(FundWalletRequest fundWalletRequest);
    Double checkWalletBalance(String email);
    Wallet makePaymentByWallet(FundWalletRequest makePaymentDto) throws InsufficientFundsException;
    Wallet withdrawFromWallet(WithdrawalDto withdrawalDto, String email) throws InsufficientFundsException;

}
