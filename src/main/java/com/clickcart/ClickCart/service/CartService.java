package com.clickcart.ClickCart.service;

import com.clickcart.ClickCart.dto.request.UpdateCartRequestDto;
import com.clickcart.ClickCart.dto.response.CartResponseDto;
import com.clickcart.ClickCart.model.Cart;

public interface CartService {

    public CartResponseDto addToCart(int user_id, int product_id, Integer quantity);
    public Cart getCartByUser(int userId);
    public void removeItem(int cartItemId);
    public CartResponseDto updateCart(int cartItemId, int qunatity);
}
