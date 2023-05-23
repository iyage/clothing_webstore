package com.example.clothingapp.configurations;

import com.example.clothingapp.models.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }
    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(AdminAccountDetail.class, AssignOrders.class,
                        Cart.class,User.class,
                        VerificationToken.class,
                        Wallet.class,
                        WalletTransaction.class,
                        StatePronvices.class,
                        ShippingAddress.class,
                        ServiceRegion.class,
                        ProductSubCategory.class,
                        ProductSearchCriteria.class,
                        ProductPage.class,
                        ProductImages.class,
                        ProductCategory.class,
                        Product.class,
                        OrderDetails.class,
                        Favourites.class,
                        CustomerOrder.class,
                        Colors.class,
                        Cities.class,
                        CategorySize.class,
                        CartItem.class,
                        Cart.class

                )
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.safariwebstore008"))
                .paths(PathSelectors.any())

                .build();
    }

}
