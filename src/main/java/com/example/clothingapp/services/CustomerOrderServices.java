package com.example.clothingapp.services;


import com.example.clothingapp.dto.CheckoutDto;
import com.example.clothingapp.models.CustomerOrder;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CustomerOrderServices {
    List<CustomerOrder> getAllDeliveries(int pageNo, int pageSize, String sortBy) throws NoSuchFieldException;
    List<CustomerOrder> getAllPendingDeliveries(int pageNo, int pageSize, String sortBy);
    List<CustomerOrder> getAllCompletedDeliveries(int pageNo, int pageSize, String sortBy);
    CustomerOrder createACustomerOrder(CheckoutDto checkoutDto, String email);
    List<CustomerOrder> viewCustomerOrderHistory(String userEmail, int pageSize, int pageNo, String sortBy);
    ResponseEntity<Map<String, Object>> getAllAssignedOrder(int pageNo, int pageSize, String sortBy);
    ResponseEntity<Map<String, Object>> getAllUnassignedOrder(int pageNo, int pageSize, String sortBy);

    CustomerOrder findParticularCustomerOrder(Long id);
}
