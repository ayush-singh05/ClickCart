package com.clickcart.ClickCart.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CartResponseDto {
    Integer cartId;

    String userName;

    String userEmail;

    List<CartItemDto> cartItems;

    Integer totalItems;

    Double totalAmount;

    Double totalDiscount; // Total savings

    Double originalAmount; // Before discount

    LocalDateTime createdAt;

    LocalDateTime updatedAt;
}
