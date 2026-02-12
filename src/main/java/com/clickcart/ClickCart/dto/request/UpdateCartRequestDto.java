package com.clickcart.ClickCart.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCartRequestDto {
    int cartItemId;
    int quantity;
}
