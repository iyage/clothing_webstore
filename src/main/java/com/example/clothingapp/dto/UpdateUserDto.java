package com.example.clothingapp.dto;


import com.example.clothingapp.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private Gender gender;
}
