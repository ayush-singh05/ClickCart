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
        return new ResponseEntity(cartService.addToCart(userId,productId,quantity),HttpStatus.OK);

    }

    @DeleteMapping("/item/delete/{cartItemId}")
    public ResponseEntity removeFromCart(@PathVariable Integer cartItemId){
            cartService.removeItem(cartItemId);
        return ResponseEntity.ok("Cart Item Remove Successfully");
    }

    @PutMapping("/item/update")
    public ResponseEntity updateCart(@RequestParam int cartItemId, @RequestParam int quantity){
        CartResponseDto cartResponseDto = cartService.updateCart(cartItemId,quantity);
        return ResponseEntity.ok(cartResponseDto);
    }

    @DeleteMapping("/item/clear/{userId}")
    public ResponseEntity clearCart(@PathVariable int userId){
        cartService.clearCart(userId);

        return ResponseEntity.ok("No items in your cart");
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity getCartByUser(@PathVariable int userId){
        CartResponseDto responseDto = cartService.getCartByUser(userId);
        return ResponseEntity.ok(responseDto);
    }
}
