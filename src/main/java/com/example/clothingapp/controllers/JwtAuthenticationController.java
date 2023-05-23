package com.example.clothingapp.controllers;


import com.example.clothingapp.configurations.JwtTokenUtil;
import com.example.clothingapp.dto.JwtRequest;
import com.example.clothingapp.dto.JwtResponse;
import com.example.clothingapp.dto.RegistrationDto;
import com.example.clothingapp.services.UserServices;
import com.example.clothingapp.services.servicesImpl.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

//import com.example.safariwebstore008.services.servicesImpl.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private UserServices userServices;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        Authentication authenticate;

        try {
            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (jwtRequest.getEmail(), jwtRequest.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());

        final String jwtToken = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(jwtToken));
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDto user) throws Exception {
            userServices.signup(user);
            return ResponseEntity.ok("User Registration Successful");
    }

    @RequestMapping(value = "/accountVerification/{token}", method = RequestMethod.GET)
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        userServices.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully", OK);
    }
}
