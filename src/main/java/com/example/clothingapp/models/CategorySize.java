package com.example.clothingapp.models;


import com.example.clothingapp.common.BaseClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category_size_table")
public class CategorySize extends BaseClass {

    @NotBlank(message = "size field is empty")
    private String size;

    @ManyToOne
    private  ProductCategory category;



}
