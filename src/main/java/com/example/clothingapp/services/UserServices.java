package com.example.clothingapp.services;


import com.example.clothingapp.dto.RegistrationDto;
import com.example.clothingapp.dto.UpdatePasswordDto;
import com.example.clothingapp.dto.UpdateUserDto;
import com.example.clothingapp.models.User;
import javax.mail.MessagingException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.security.auth.login.AccountNotFoundException;


@Service
public interface UserServices {

    User signup(RegistrationDto registrationDto) throws MessagingException, MessagingException;

    void verifyAccount(String token);

    User updateUser(UpdateUserDto updateUserDto, String email) throws AccountNotFoundException;

    User updatePassword(UpdatePasswordDto passwordRestDto, String email) throws Exception;




}
