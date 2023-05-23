package com.example.clothingapp.repositories;

import com.example.clothingapp.models.ServiceRegion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<ServiceRegion, Long> {

    ServiceRegion findServiceRegionByRegionName(String name);

}
