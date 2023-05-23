package com.example.clothingapp.services.servicesImpl;

import com.example.clothingapp.models.ServiceRegion;
import com.example.clothingapp.repositories.RegionRepository;
import com.example.clothingapp.services.Regionservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionServiceImpl implements Regionservice {
    @Autowired
    RegionRepository regionRepository;
    @Override
    public ServiceRegion findServiceRegionByName(String name) {
        return regionRepository.findServiceRegionByRegionName(name);

    }
}
