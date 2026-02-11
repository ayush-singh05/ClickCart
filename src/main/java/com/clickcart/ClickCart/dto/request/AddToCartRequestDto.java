package com.clickcart.ClickCart.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AddToCartRequestDto {
    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be positive")
    Integer userId;

    @NotNull(message = "Product ID is required")
    @Positive(message = "Product ID must be positive")
    Integer productId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 100, message = "Quantity cannot exceed 100")
    Integer quantity;
}
