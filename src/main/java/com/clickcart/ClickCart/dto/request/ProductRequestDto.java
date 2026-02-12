package com.clickcart.ClickCart.dto.request;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductRequestDto {

    String productName;

    String productDescription;

    double price;

    double originalPrice;

    String brand;

    int availableQuantity;

    int stockQuantity;

    String category;

    boolean active = true;

    String productImage;
}

/*
*

    String productName;
    String productDescription;

    String productImage;

    String brand;
    Double price;

    Double originalPrice; // For showing discount

    String category;
    Integer availableQuantity;

    boolean active;
    int stockQuantity;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;
* */