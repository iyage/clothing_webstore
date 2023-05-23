package com.example.clothingapp.controllers;


import com.example.clothingapp.models.CustomerOrder;
import com.example.clothingapp.models.ShippingAddress;
import com.example.clothingapp.repositories.CustomerOrderRepository;
import com.example.clothingapp.repositories.ShippingAdressRepository;
import com.example.clothingapp.services.servicesImpl.AssignOrderToDispatchRider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.mail.MessagingException;
import java.util.Date;

@RestController
public class assignOrdersTestController {
        @Autowired
        AssignOrderToDispatchRider assignOrderToDispatchRider;
    @Autowired
    CustomerOrderRepository customerOrderRepository;
    @Autowired
    ShippingAdressRepository shippingAddressRepository;
    @GetMapping("/assignorder")
    public void assignOrderToDispatchRider() throws MessagingException {
        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setEmail("jummyhanna@gmail.com");
        shippingAddress.setFirstName("olawale");
        shippingAddress.setLastName("deji");
        shippingAddress.setPhoneNumber("080263095");
        shippingAddress.setCityName("ikorodu");
        shippingAddress.setRegionName("A");
        shippingAddress.setHomeAdddress("10 adebowale street");
        shippingAddressRepository.save(shippingAddress);
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setShippingAddress(shippingAddress);
        customerOrder.setDeliveryDate(new Date());
        customerOrderRepository.save(customerOrder);
        assignOrderToDispatchRider.assignOrderToDispatchRider(customerOrder);
        System.out.println(shippingAddress.toString());
    }
}
