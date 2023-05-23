package com.example.clothingapp.services.servicesImpl;

import com.example.clothingapp.models.AssignOrders;
import com.example.clothingapp.repositories.AssignedOrderRepository;
import com.example.clothingapp.services.AssignOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignOrderServiceImp implements AssignOrderService {
    @Autowired
    AssignedOrderRepository assignedOrderRepository;
    @Override
    public AssignOrders saveAssignOrders(AssignOrders assignOrders) {
        return assignedOrderRepository.save(assignOrders) ;
    }
}
