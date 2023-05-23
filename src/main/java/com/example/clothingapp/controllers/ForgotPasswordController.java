package com.example.clothingapp.controllers;

import com.example.clothingapp.dto.ResetPasswordDto;
import com.example.clothingapp.models.User;
import com.example.clothingapp.services.ForgotPasswordService;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasAuthority('CUSTOMER')")
@RestController
public class ForgotPasswordController {
    @Autowired
    ForgotPasswordService forgotPasswordService;

    @GetMapping("/generateResetToken")

    public ResponseEntity<String> generateResetToken(@RequestParam(name = "email") String email) {
        String response = "";
        try {
            forgotPasswordService.generateResetToken(email);
        } catch (MessagingException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Token not generated", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Token generated successfully", HttpStatus.OK);
    }


    @PostMapping("/resetPassword/{token}")
    public ResponseEntity<User> resetPassword(@PathVariable String token, @RequestBody ResetPasswordDto resetPasswordDto) {
        User user = forgotPasswordService.resetPassword(resetPasswordDto, token);
        return new ResponseEntity<>(user,  HttpStatus.OK);
    }
}
