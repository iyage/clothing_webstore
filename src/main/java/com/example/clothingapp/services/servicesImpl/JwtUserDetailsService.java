package com.example.clothingapp.services.servicesImpl;

import com.example.clothingapp.dto.MyUserDetails;
import com.example.clothingapp.exceptions.AccountNotEnabledException;
import com.example.clothingapp.models.User;
import com.example.clothingapp.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailsService  implements  UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
          Optional<User> userModel = userRepository.findUserByEmail(userEmail);
          User user = userModel.orElseThrow(() ->
                  new UsernameNotFoundException("No user found with email : " + userEmail));

            if(user.getIsEnabled()){
                return new MyUserDetails(user);
            }
            throw new AccountNotEnabledException("Account is disabled");
        }
}
