package com.example.clothingapp.repositories;


import com.example.clothingapp.models.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingAdressRepository extends JpaRepository<ShippingAddress, Long> {
}
