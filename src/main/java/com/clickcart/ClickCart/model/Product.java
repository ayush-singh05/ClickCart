package com.clickcart.ClickCart.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer productId;

    @Column(nullable = false)
    String productName;

    @Column(length = 1000)
    String productDescription;

    String productImage;

    String brand;

    @Column(nullable = false)
    Double price;

    Double originalPrice; // For showing discount

    String category;

    @Column(name = "available_quantity",nullable = false)
    Integer availableQuantity;

    boolean active;

    @Column(nullable = false)
    int stockQuantity;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    @PrePersist
    public void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
