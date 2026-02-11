package com.clickcart.ClickCart.service;

import com.clickcart.ClickCart.dto.request.UserLoginRequestDto;
import com.clickcart.ClickCart.dto.request.UserRequestDTO;
import com.clickcart.ClickCart.dto.response.UserLoginResponse;
import com.clickcart.ClickCart.dto.response.UserResponseDTO;
import org.springframework.http.HttpStatusCode;

public interface UserService{
    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) ;

}
