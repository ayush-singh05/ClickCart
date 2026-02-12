package com.clickcart.ClickCart.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class ProductResponseDto {
    Integer productId;
    String productName;
    String category;
    String description;
    Integer availableQuantity;
    String brand;
    Integer stockQuantity;
    Double price;
    Double originalPrice;
    Boolean active;
    LocalDateTime createdAt;
    LocalDateTime updateAt;
}
