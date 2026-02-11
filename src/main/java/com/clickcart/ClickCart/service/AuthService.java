package com.clickcart.ClickCart.service;

import com.clickcart.ClickCart.dto.request.UserLoginRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    public String login(UserLoginRequestDto userLoginRequestDto);
}
