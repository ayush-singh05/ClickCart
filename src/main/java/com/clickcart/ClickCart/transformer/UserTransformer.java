package com.clickcart.ClickCart.transformer;

import com.clickcart.ClickCart.dto.request.UserRequestDTO;
import com.clickcart.ClickCart.dto.response.UserResponseDTO;
import com.clickcart.ClickCart.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserTransformer {


    public static User userRequestDtoToUser(UserRequestDTO dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .mobile(dto.getMobile())
                .userName(dto.getUserName())
                .password(dto.getPassword())
                .gender(dto.getGender())
                .build();
    }

    public static UserResponseDTO userToUserResponseDto(User user) {
        return UserResponseDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .userName(user.getUserName())
                .build();
    }
}
