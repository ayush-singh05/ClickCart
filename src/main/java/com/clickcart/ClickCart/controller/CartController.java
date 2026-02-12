package com.clickcart.ClickCart.controller;

import com.clickcart.ClickCart.dto.request.UpdateCartRequestDto;
import com.clickcart.ClickCart.dto.response.CartResponseDto;
import com.clickcart.ClickCart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestParam int userId,@RequestParam int productId,@RequestParam Integer quantity){
        try {
            return new ResponseEntity(cartService.addToCart(userId,productId,quantity),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity removeFromCart(@PathVariable Integer cartItemId){
            cartService.removeItem(cartItemId);
        return ResponseEntity.ok("Cart Item Remove Successfully");
    }

    @PostMapping("/item/update")
    public ResponseEntity updateCart(@RequestBody UpdateCartRequestDto request){
        CartResponseDto cartResponseDto = cartService.updateCart(request.getCartItemId(),request.getCartItemId());
        return ResponseEntity.ok(cartResponseDto);
    }
}
