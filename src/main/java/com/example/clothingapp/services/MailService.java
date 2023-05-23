package com.example.clothingapp.services;


import com.example.clothingapp.dto.MailDto;
import javax.mail.MessagingException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;


@Service
public interface MailService {

    void sendMail(MailDto mailDto) throws MessagingException, MessagingException;
}
