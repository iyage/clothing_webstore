package com.example.clothingapp.controllers;


import com.example.clothingapp.models.Product;
import com.example.clothingapp.models.ProductPage;
import com.example.clothingapp.models.ProductSearchCriteria;
import com.example.clothingapp.services.servicesImpl.ProductFilterServiceimpl;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myProducts")
public class ProductFilterController {
    private final ProductFilterServiceimpl myProductService;

    public ProductFilterController(ProductFilterServiceimpl myProductService) {
        this.myProductService = myProductService;
    }
    @GetMapping("/filterAndSortProducts")
    public ResponseEntity<Page<Product>> getMyProducts(ProductPage myProductPage,
                                                       ProductSearchCriteria myProductSearchCriteria){
        return new ResponseEntity<>(myProductService.getProducts(myProductPage,
                myProductSearchCriteria), HttpStatus.OK);
    }
}
