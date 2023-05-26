package com.example.clothingapp.configurations;

import com.example.clothingapp.models.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
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
                .apiInfo(apiInfo())
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
                .apis(RequestHandlerSelectors.basePackage("com.example.clothingapp"))
                .paths(PathSelectors.any())

                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfo(
                "WebStore  API",
                "<h2 style='margin:2px'> Author: Ofunrein Iyaghe.</h2> " +
                        "<article> <h2>Description </h2>" +
                        "This a clothing webstore app<br/>" +
                        "<h2>Demo Usage</h2> " +
                        "<h1>Authorization Header setting: Bearer +token</h1> " +
                        "<article>.<br/>",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("Ofunrein Iyaghe", "https://github.com/iyage/clothing_webstore", "yahg.concept@gmail.com"),
                "API License",
                "https://github.com/iyage",
                Collections.emptyList());
    }

}
