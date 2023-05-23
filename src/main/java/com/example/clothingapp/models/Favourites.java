package com.example.clothingapp.models;


import com.example.clothingapp.common.BaseClass;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_favourites_table")
public class Favourites extends BaseClass {


    @OneToOne
    private User userModel;
    @ManyToMany
    private List<Product> products;

}
