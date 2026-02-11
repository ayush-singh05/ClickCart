package com.clickcart.ClickCart.controller;

import com.clickcart.ClickCart.dto.request.UserLoginRequestDto;
import com.clickcart.ClickCart.service.AuthService;
import com.clickcart.ClickCart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    AuthService authService;



    @GetMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginRequestDto userLoginDto) {

        try{
            return new ResponseEntity(authService.login(userLoginDto), HttpStatus.ACCEPTED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
    }

}
