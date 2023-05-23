package com.example.clothingapp.models;


import com.example.clothingapp.common.BaseClass;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignOrders extends BaseClass {
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @OneToOne
    private CustomerOrder orders;
}
