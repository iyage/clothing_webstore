package com.example.clothingapp.services;


import com.example.clothingapp.models.ServiceRegion;

public interface Regionservice {
ServiceRegion findServiceRegionByName(String name);
}
