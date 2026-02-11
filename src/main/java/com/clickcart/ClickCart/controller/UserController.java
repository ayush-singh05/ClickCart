package com.clickcart.ClickCart.controller;

import com.clickcart.ClickCart.dto.request.UserLoginRequestDto;
import com.clickcart.ClickCart.dto.request.UserRequestDTO;

import com.clickcart.ClickCart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/add-user")
    public ResponseEntity addUser(@RequestBody UserRequestDTO userRequestDTO){

            try{
                return new ResponseEntity(userService.addUser(userRequestDTO),HttpStatus.ACCEPTED);
            }catch (Exception e){
                return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
            }

    }

}
