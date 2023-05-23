package com.example.clothingapp.models;

import com.example.clothingapp.common.BaseClass;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CartItem extends BaseClass {

    private Integer quantity;

    private BigInteger price;
    @OneToOne
    private Product product;

    @ManyToOne
    private Cart cart;
}
