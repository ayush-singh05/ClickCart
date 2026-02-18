package com.clickcart.ClickCart.service;

import com.clickcart.ClickCart.dto.request.UpdateCartRequestDto;
import com.clickcart.ClickCart.dto.response.CartResponseDto;
import com.clickcart.ClickCart.model.Cart;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    public CartResponseDto addToCart(int userId, int productId, Integer quantity);
    public CartResponseDto getCartByUser(int userId);
    public void removeItem(int cartItemId);
    public CartResponseDto updateCart(int cartItemId, int quantity);

    void clearCart(int userId);

}
