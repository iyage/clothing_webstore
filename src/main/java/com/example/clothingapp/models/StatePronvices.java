package com.example.clothingapp.models;


import com.example.clothingapp.common.BaseClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "state_table")
@Entity
public class StatePronvices extends BaseClass {

    @NotEmpty(message = "state field is empty")
    private String stateName;

    @OneToMany
    private List<Cities> listOfCitiesInAState;
}
