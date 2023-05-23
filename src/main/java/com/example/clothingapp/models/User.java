package com.example.clothingapp.models;


import com.example.clothingapp.common.BaseClass;
import com.example.clothingapp.enums.Gender;
import com.example.clothingapp.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Builder
@Data
@Entity
@AllArgsConstructor
@Table(name = "users_table", uniqueConstraints = @UniqueConstraint(name = "UniqueEmail", columnNames = "email")
)
public  class User extends BaseClass {

    @NotEmpty(message = "first-name field is empty")
    private String firstName;

    @NotEmpty(message = "last-name is empty")
    private String lastName;

    @DateTimeFormat(pattern = "dd/mm/yyyy")
    @NotEmpty(message = "date field is empty")
    private Date dateOfBirth;

    @Email( message = "email field is not properly formatted")
    @NotEmpty(message = "email field is empty")
    private String email;

    @NotEmpty(message = "gender field is empty")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Roles roles;
    @NotEmpty(message = "password field is empty")
    @Valid
    private String password;
    private Boolean isEnabled;

    public User(Long id) {
        super(id);
    }
    public User(){

    }
}
