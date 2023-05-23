package com.example.clothingapp.services.servicesImpl;

import com.example.clothingapp.dto.MailDto;
import com.example.clothingapp.dto.RegistrationDto;
import com.example.clothingapp.dto.UpdatePasswordDto;
import com.example.clothingapp.dto.UpdateUserDto;
import com.example.clothingapp.enums.Gender;
import com.example.clothingapp.enums.Roles;
import com.example.clothingapp.exceptions.CustomAppException;
import com.example.clothingapp.models.User;
import com.example.clothingapp.models.VerificationToken;
import com.example.clothingapp.repositories.UserRepository;
import com.example.clothingapp.repositories.VerificationTokenRepository;
import com.example.clothingapp.services.UserServices;

import javax.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import javax.security.auth.login.AccountNotFoundException;
import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;


@Component
@Slf4j
@Validated
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private MailServiceImpl mailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User updatePassword(UpdatePasswordDto updatePasswordDto, String email) throws Exception {
        User user1 = userRepository.findUserByEmail(email).get();
        user1.setPassword(passwordEncoder.encode(updatePasswordDto.getNewPassword()));
        userRepository.save(user1);
        return user1;
    }


    @Override
    public User updateUser(UpdateUserDto updateUserDto, String email) throws AccountNotFoundException {
        Optional<User> optionalUsers = userRepository.findUserByEmail(email);
        if(optionalUsers.isPresent()){
            User customer = optionalUsers.get();
            customer.setFirstName(updateUserDto.getFirstName());
            customer.setLastName(updateUserDto.getLastName());
            customer.setEmail(updateUserDto.getEmail());
            customer.setGender(updateUserDto.getGender());
            customer.setDateOfBirth(updateUserDto.getDateOfBirth());
            return userRepository.save(customer);
        } else{
            throw new AccountNotFoundException("User account not found");
        }
    }

    @Override
    public User signup(@Valid RegistrationDto registrationDto) throws MessagingException {
        Optional<User> optionalEmail = userRepository.findUserByEmail(registrationDto.getEmail());

        if (registrationDto.getPassword().length() < 8) {
           throw new CustomAppException("password cannot be less than eight");
        }
        User user = new User();
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setDateOfBirth(registrationDto.getDateOfBirth());
        Gender gender;
        if(registrationDto.getGender().equalsIgnoreCase("Male")){
            gender = Gender.MALE;
        } else
            gender = Gender.FEMALE;
        user.setGender(gender);
        user.setRoles(Roles.CUSTOMER);
        user.setIsEnabled(false);

        if (optionalEmail.isEmpty()){
            userRepository.save(user);
        } else
            throw new CustomAppException("Email: " + registrationDto.getEmail() + " already exist");


        String token = generateVerificationToken(user);
        MailDto mailDto =  new MailDto();
        mailDto.setTo(user.getEmail());
        mailDto.setSubject("Please Activate your Account");
        mailDto.setBody("Thank you for signing up to Safari Webstore, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8080/accountVerification/" + token);
        try {
            mailService.sendMail(mailDto);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return user;
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    @Override
    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new CustomAppException("Invalid Token")));
    }


    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String email = verificationToken.getUser().getEmail();
        User user = userRepository.findUserByEmail(email).orElseThrow(() ->
                new CustomAppException("User not found with name - " + email));
        user.setIsEnabled(true);
        userRepository.save(user);
    }
}
