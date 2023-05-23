package com.example.clothingapp.repositories;


import com.example.clothingapp.models.AssignOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignedOrderRepository extends JpaRepository<AssignOrders, Long> {
}
