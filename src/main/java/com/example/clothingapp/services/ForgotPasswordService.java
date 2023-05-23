package com.example.clothingapp.services;


import com.example.clothingapp.dto.ResetPasswordDto;
import com.example.clothingapp.models.User;
import javax.mail.MessagingException;

import javax.mail.MessagingException;


public interface ForgotPasswordService {
    boolean generateResetToken(String email) throws MessagingException, MessagingException, MessagingException;

    User resetPassword(ResetPasswordDto resetPasswordDto, String token);
}
