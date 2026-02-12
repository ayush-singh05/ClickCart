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
    String productName;
    String category;
    String description;
    int availableQuantity;
    String brand;
    int stockQuantity;
    double price;
    double originalPrice;
    boolean active;
    LocalDateTime createdAt;
    LocalDateTime updateAt;
}
