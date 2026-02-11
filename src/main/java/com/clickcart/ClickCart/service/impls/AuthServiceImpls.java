package com.clickcart.ClickCart.service.impls;

import com.clickcart.ClickCart.dto.request.UserLoginRequestDto;
import com.clickcart.ClickCart.exception.UserOrPasswordIncorrectException;
import com.clickcart.ClickCart.model.User;
import com.clickcart.ClickCart.repository.UserRepository;
import com.clickcart.ClickCart.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.UnknownServiceException;
@Service
public class AuthServiceImpls implements AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public String login(UserLoginRequestDto userLoginRequestDto) {
        User user = userRepository.findByEmail(userLoginRequestDto.getEmail());

            if(user == null) {
                throw new UserOrPasswordIncorrectException("Invalid user or password");
            }
        boolean isPasswordCorrect = passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword());
        if(!isPasswordCorrect) {
            throw new UserOrPasswordIncorrectException("Password is incorrect");
        }

        return "Welcome "+user.getName();
    }
}
