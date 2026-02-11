package com.clickcart.ClickCart.dto.request;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductRequestDto {
    String productName;

    String description;

    double price;

    int stockQuantity;

    String category;

    boolean active = true;

    String productImage;
}
