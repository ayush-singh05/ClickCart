package com.clickcart.ClickCart.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class ProductResponseDto {
    String productName;
    String category;
    String description;
    int stockQuantity;
}
