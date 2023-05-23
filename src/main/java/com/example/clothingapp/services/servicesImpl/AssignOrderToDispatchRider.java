package com.example.clothingapp.services.servicesImpl;



import com.example.clothingapp.dto.MailDto;
import com.example.clothingapp.enums.OrderAssigStatus;
import com.example.clothingapp.models.*;
import com.example.clothingapp.repositories.CustomerOrderRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@Data
public class AssignOrderToDispatchRider {
    @Autowired
    CustomerOrderRepository customerOrderRepository;
    @Autowired
    RegionServiceImpl regionService;
    @Autowired
    AssignOrderServiceImp assignOrderServiceImp;
    @Autowired
    MailServiceImpl mailService;

    public void  assignOrderToDispatchRider(CustomerOrder customerOrder) throws MessagingException {
      ShippingAddress shippingAddress = customerOrder.getShippingAddress();
        String regionName =  shippingAddress.getRegionName();
        System.out.println(regionName);
        ServiceRegion regionAssigned =regionService.findServiceRegionByName(regionName);
        User dispatchRider = regionAssigned.getUser();
        AssignOrders assignOrders = new AssignOrders();
        assignOrders.setOrders(customerOrder);
        assignOrders.setUser(dispatchRider);
        customerOrder.setStatus(OrderAssigStatus.ASSIGNED);
    customerOrderRepository.save(customerOrder);
        assignOrderServiceImp.saveAssignOrders(assignOrders);
       String dispatchRiderEmail = dispatchRider.getEmail();
      String deliveryDate = String.valueOf(customerOrder.getDeliveryDate());
      String shippingInfo = shippingAddress.toString() +
              "\n"+ deliveryDate;
        MailDto assignOrderNotification = new MailDto(dispatchRiderEmail,"New Assign order",shippingInfo);
      mailService.sendMail(assignOrderNotification);

    }

}
