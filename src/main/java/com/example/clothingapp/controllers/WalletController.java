package com.example.clothingapp.controllers;


import com.example.clothingapp.configurations.JwtTokenUtil;
import com.example.clothingapp.dto.FundWalletRequest;
import com.example.clothingapp.dto.WithdrawalDto;
import com.example.clothingapp.exceptions.InsufficientFundsException;
import com.example.clothingapp.models.Wallet;
import com.example.clothingapp.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;

@RestController
@PreAuthorize("hasAuthority('CUSTOMER')")
@RequestMapping("/user")
public class WalletController {
    @Autowired
    private WalletService walletService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @PostMapping("/wallet")
    public ResponseEntity<Wallet> fundWallet(@RequestBody FundWalletRequest fundWalletRequest,
                                             HttpServletRequest request){
        Wallet wallet = walletService.topUpWalletAccount(fundWalletRequest);
        return new ResponseEntity<>(wallet,HttpStatus.OK);
    }

    @GetMapping("/walletBalance")
    public ResponseEntity<Double> checkWalletBalance(HttpServletRequest request) throws ServletException, IOException {
        String token = request.getHeader("Authorization").split(" ")[1];
        String email = jwtTokenUtil.getUserEmailFromToken(token);
        Double walletBalance = walletService.checkWalletBalance(email);
        return new ResponseEntity<>(walletBalance, HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdrawFromWallet (@RequestBody WithdrawalDto withdrawalDto, HttpServletRequest request) throws InsufficientFundsException {
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        try {
            walletService.withdrawFromWallet(withdrawalDto, email);
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch (InsufficientFundsException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }


    }

}
