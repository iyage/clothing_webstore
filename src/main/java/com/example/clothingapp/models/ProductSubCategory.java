package com.example.clothingapp.models;

import com.example.clothingapp.common.BaseClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_subcategory_table")
public class ProductSubCategory extends BaseClass {

    @NotNull(message="category name is empty")
    private String subCategoryName;
    @ManyToOne
    private  ProductCategory category;
}
