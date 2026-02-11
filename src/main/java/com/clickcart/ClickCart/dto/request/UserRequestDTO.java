package com.clickcart.ClickCart.dto.request;

import com.clickcart.ClickCart.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDTO {
    String name;
    String email;
    String mobile;
    Gender gender;
    String userName;
    String password;
}
