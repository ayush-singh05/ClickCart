package com.clickcart.ClickCart.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductRequestDto {

    String productName;

    String productDescription;

    Double price;

    Double originalPrice;

    String brand;

    Integer availableQuantity;

    Integer stockQuantity;

    String category;

    Boolean active = true;

    String productImage;
}