package com.example.clothingapp.models;

import com.example.clothingapp.common.BaseClass;
import com.example.clothingapp.enums.DeliveryMethod;
import com.example.clothingapp.enums.DeliveryStatus;
import com.example.clothingapp.enums.OrderAssigStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerOrder extends BaseClass {
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private Date deliveryDate;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
    private BigInteger deliveryFee;
    @ManyToOne
    private ShippingAddress shippingAddress;
    @Enumerated(EnumType.STRING)
    private OrderAssigStatus status;
    @Enumerated(EnumType.STRING)
    private DeliveryMethod deliveryMethod;
    private BigInteger totalOrderAmount;
    @ManyToOne
    @JsonIgnore
    private User userModel;
    @OneToMany
    private List<OrderDetails> orderDetailsList;

}
