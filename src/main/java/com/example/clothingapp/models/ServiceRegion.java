package com.example.clothingapp.models;


import com.example.clothingapp.common.BaseClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class ServiceRegion extends BaseClass {
   private String regionName;
   @OneToMany
   private List<Cities> CitiesWithinARegion;
   @OneToOne
   private User user;
}
