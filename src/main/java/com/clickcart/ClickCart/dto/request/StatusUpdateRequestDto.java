package com.clickcart.ClickCart.dto.request;

import com.clickcart.ClickCart.Enum.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatusUpdateRequestDto {
    OrderStatus orderStatus;
}
