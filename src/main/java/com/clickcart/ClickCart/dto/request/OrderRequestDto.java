package com.clickcart.ClickCart.dto.request;

import com.clickcart.ClickCart.model.OrderItem;
import com.clickcart.ClickCart.model.ShippingAddress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequestDto {
    @NotBlank(message = "User ID is required")
    String userId;

    @NotEmpty(message = "Items list cannot be empty")
    @Valid
    List<OrderItem> items;

    @NotNull(message = "Shipping address is required")
    @Valid
    ShippingAddress shippingAddress;

    String paymentMethod;
}
