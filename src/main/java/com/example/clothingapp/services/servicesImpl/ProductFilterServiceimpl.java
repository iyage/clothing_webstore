package com.example.clothingapp.services.servicesImpl;



import com.example.clothingapp.models.Product;
import com.example.clothingapp.models.ProductPage;
import com.example.clothingapp.models.ProductSearchCriteria;
import com.example.clothingapp.repositories.ProductFilterCriteriaRepository;
import com.example.clothingapp.repositories.ProductFilterRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Data
@Service
public class ProductFilterServiceimpl {
    @Autowired
   private ProductFilterRepository myProductRepository;
    @Autowired
   private ProductFilterCriteriaRepository myProductCriteriaRepository;

    public ProductFilterServiceimpl(ProductFilterRepository myProductRepository, ProductFilterCriteriaRepository myProductCriteriaRepository) {
        this.myProductRepository = myProductRepository;
        this.myProductCriteriaRepository = myProductCriteriaRepository;
    }
    public Page<Product> getProducts(ProductPage myProductPage, ProductSearchCriteria myProductSearchCriteria){

       return myProductCriteriaRepository.findAllProductWithFilter(myProductPage, myProductSearchCriteria);
    }

    public Product addMyProduct(Product myProduct){
        return myProductRepository.save(myProduct);
    }
}
