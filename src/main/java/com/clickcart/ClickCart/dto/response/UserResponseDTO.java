package com.clickcart.ClickCart.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResponseDTO {

    String name;
    String email;
    String mobile;
    String userName;

}
