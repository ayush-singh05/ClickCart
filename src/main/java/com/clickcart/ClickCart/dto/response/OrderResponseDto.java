package com.clickcart.ClickCart.dto.response;

import com.clickcart.ClickCart.Enum.OrderStatus;
import com.clickcart.ClickCart.model.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Builder
public class OrderResponseDto {

    Long orderId;

    User user;

    BigDecimal amount;

    OrderStatus status;
}
