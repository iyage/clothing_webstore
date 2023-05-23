package com.example.clothingapp.models;


import com.example.clothingapp.common.BaseClass;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cities_lga_table")
@Entity
public class Cities extends BaseClass {
    @NotNull(message = "city-name field is empty")
    private String cityName;

}
