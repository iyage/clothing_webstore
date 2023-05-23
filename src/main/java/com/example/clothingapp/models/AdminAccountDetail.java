package com.example.clothingapp.models;

//import com.example.clothshoping.common.BaseClass;
//import com.example.clothshoping.enums.AccountType;
import com.example.clothingapp.common.BaseClass;
import com.example.clothingapp.enums.AccountType;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admin_account_details_table")
public class AdminAccountDetail extends BaseClass {
    @NotNull(message = "bank name field is empty field is empty")
    private String bankName;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @NotNull(message = "Account number field is empty")
    private String accountNumber;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private User user;
}
