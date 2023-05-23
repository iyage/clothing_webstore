package com.example.clothingapp.dto;

import lombok.Data;

@Data
public class UpdatePasswordDto {
    public String newPassword;
    public String oldPassword;
}
