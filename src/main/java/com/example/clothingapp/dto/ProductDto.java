package com.example.clothingapp.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Builder
@Data
public class ProductDto {

    private String productName;
    private String description;
    private BigInteger price;
    private String color;
    private String size;
    private String categoryName;
    private String subCategoryName;
}
