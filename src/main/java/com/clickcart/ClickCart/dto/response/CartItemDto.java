package com.clickcart.ClickCart.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemDto {

        Integer cartItemId;

        Integer productId;

        String productName;

        String productDescription;

        String productImage;

        String productBrand;

        String productCategory;

        Integer quantity;

        Double unitPrice;

        Double originalPrice; // Before discount

        Double discount; // Discount percentage

        Double totalPrice; // quantity * unitPrice

        Integer availableStock;

        Boolean inStock;
}
