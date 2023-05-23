package com.example.clothingapp.controllers;

import com.example.clothingapp.configurations.JwtTokenUtil;
import com.example.clothingapp.dto.CheckoutDto;
import com.example.clothingapp.dto.UpdatePasswordDto;
import com.example.clothingapp.dto.UpdateUserDto;
import com.example.clothingapp.models.CustomerOrder;
import com.example.clothingapp.models.Product;
import com.example.clothingapp.models.User;
import com.example.clothingapp.services.CustomerOrderServices;
import com.example.clothingapp.services.FavouriteService;
import com.example.clothingapp.services.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@PreAuthorize("hasAuthority('CUSTOMER')")
@RequestMapping("/api/customer")
public class UserController {
    UriComponentsBuilder uriComponentsBuilder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserServices userServices;
    private final CustomerOrderServices customerOrderService;
    private final FavouriteService favouriteService;

    @Autowired
    public UserController(UserServices userServices, AuthenticationManager authenticationManager,
                          JwtTokenUtil jwtTokenUtil,CustomerOrderServices customerOrderService,FavouriteService favouriteService) {
        this.userServices = userServices;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
        this.customerOrderService = customerOrderService;
        this.favouriteService = favouriteService;


    }

    @PutMapping("/updatePassword")
    private ResponseEntity<com.example.clothingapp.models.User> updatePassword(@RequestBody UpdatePasswordDto updatePasswordDto, HttpServletRequest request) throws Exception {
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtTokenUtil.getUserEmailFromToken(token);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, updatePasswordDto.oldPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        User user = userServices.updatePassword(updatePasswordDto, email);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

  
    @PostMapping("/checkout")
    private ResponseEntity<?> createACustomerOrder(@RequestBody CheckoutDto checkoutDto, HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtTokenUtil.getUserEmailFromToken(token);
       CustomerOrder customerOrder=customerOrderService.createACustomerOrder(checkoutDto,email);
       if(customerOrder!=null){
       return new ResponseEntity<>(customerOrder,HttpStatus.CREATED);
       }else{
           return new ResponseEntity<>("User not allowed",HttpStatus.FORBIDDEN);
       }
    }
    
    @GetMapping("/orderHistory")
    private ResponseEntity<?> viewOrderHistory(@RequestParam(defaultValue = "0")int pageNo,
                                               @RequestParam(defaultValue = "5") int pageSize,
                                               @RequestParam(defaultValue = "deliveryDate") String sortByDate, HttpServletRequest request){

         String requestTokenHeader = request.getHeader("Authorization");
         String jwtToken = requestTokenHeader.substring(7);
         String userEmail= jwtTokenUtil.getUserEmailFromToken(jwtToken);

         List<CustomerOrder> customerOrderPage= customerOrderService.viewCustomerOrderHistory(userEmail,pageNo,pageSize,sortByDate);

         return new ResponseEntity<>(customerOrderPage,HttpStatus.ACCEPTED);

    }

    @GetMapping("/viewFavouriteProducts")
    private ResponseEntity<List<Product>> viewAllProductsFromFavourite(HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtTokenUtil.getUserEmailFromToken(token);
        List<Product> productList = favouriteService.viewAllProductFromFavourite(email);
        return new ResponseEntity<>(productList,HttpStatus.OK);
    }

    @GetMapping("/viewProductFromFavourite/{id}")
    private ResponseEntity<Product> viewAProductFromFavourite(@PathVariable Long id, HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtTokenUtil.getUserEmailFromToken(token);
        Product product = favouriteService.viewProductFromFavourite(id,email);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @PutMapping("/edit/{userEmail}")
    public ResponseEntity<User> editCustomer(@RequestBody UpdateUserDto editUserDto, @PathVariable("userEmail") String email) throws AccountNotFoundException {
        User users = userServices.updateUser(editUserDto,email);
        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }


    @GetMapping("/customerOrders/{id}")
    public ResponseEntity<CustomerOrder> getCustomerOrder(@PathVariable Long id){
        CustomerOrder customerOrder = customerOrderService.findParticularCustomerOrder(id);
        return new ResponseEntity<>(customerOrder, HttpStatus.OK);
    }

}
