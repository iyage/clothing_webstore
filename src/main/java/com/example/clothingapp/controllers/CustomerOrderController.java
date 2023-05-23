package com.example.clothingapp.controllers;



import com.example.clothingapp.models.CustomerOrder;
import com.example.clothingapp.services.CustomerOrderServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@PreAuthorize("hasAuthority('CUSTOMER')")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderServices customerOrderServices;

    @GetMapping("/all")
    @ApiOperation(
            value = "Allows dispatch riders to view all deliveries",
            response = CustomerOrder.class)
    public ResponseEntity<List<CustomerOrder>> getAllDeliveries(
            @RequestParam(defaultValue="0") int pageNo,
            @RequestParam(defaultValue="5") int pageSize,
            @RequestParam(defaultValue="id") String sortBy) throws NoSuchFieldException {

        List<CustomerOrder> customerOrderList =  customerOrderServices.getAllDeliveries(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(customerOrderList,HttpStatus.ACCEPTED);
    }

    @GetMapping("/pending")
    @ApiOperation(
            value = "Allows dispatch riders to view all pending deliveries",
            response = CustomerOrder.class)
    public ResponseEntity<List<CustomerOrder>> getAllPendingDeliveries(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
    List<CustomerOrder> customerPendingOrders = customerOrderServices.getAllPendingDeliveries(pageNo, pageSize, sortBy);
    return new ResponseEntity<>(customerPendingOrders, HttpStatus.ACCEPTED);
    }

    @GetMapping("/delivered")
    @ApiOperation(
            value = "Allows dispatch riders to view all deliveries that has been completed",
            response = CustomerOrder.class)
    public ResponseEntity<List<CustomerOrder>> getAllCompletedDeliveries(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {

        List<CustomerOrder> customerDelivered = customerOrderServices.getAllCompletedDeliveries(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(customerDelivered, HttpStatus.ACCEPTED);
    }

    @GetMapping("/order-assigned")
    @ApiOperation(
            value = "Allows Admin to view orders that have been assigned to dispatch rider",
            response = CustomerOrder.class)
    public ResponseEntity<Map<String, Object>> getAllOrdersAssignedToDispatch(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy){

        return customerOrderServices.getAllAssignedOrder(pageNo, pageSize, sortBy);
    }

    @GetMapping("/order-unassigned")
    @ApiOperation(
            value = "Allows Admin to view orders that have not been assigned to dispatch rider",
            response = CustomerOrder.class)
    public ResponseEntity<Map<String, Object>> getAllOrdersUnassignedToDispatch(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy){

        return customerOrderServices.getAllUnassignedOrder(pageNo, pageSize, sortBy);
    }
}
