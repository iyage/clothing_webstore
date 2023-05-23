package com.example.clothingapp.repositories;


import com.example.clothingapp.enums.DeliveryStatus;
import com.example.clothingapp.enums.OrderAssigStatus;
import com.example.clothingapp.models.CustomerOrder;
import com.example.clothingapp.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
    Page<CustomerOrder> findByDeliveryStatus(DeliveryStatus deliveryStatus, Pageable pageable);
    Page<CustomerOrder> findAllByUserModel(User userModel, Pageable pageable);
    CustomerOrder findCustomerOrderById(Long id);
    Page<CustomerOrder> findByStatus(OrderAssigStatus status, Pageable pageable);

}
