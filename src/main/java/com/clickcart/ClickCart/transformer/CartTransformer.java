package com.clickcart.ClickCart.transformer;

import com.clickcart.ClickCart.dto.response.CartItemDto;
import com.clickcart.ClickCart.dto.response.CartResponseDto;
import com.clickcart.ClickCart.model.Cart;

import java.util.List;
import java.util.stream.Collectors;

public class CartTransformer {

    public static CartResponseDto cartToCartResponseDto(Cart cart) {
        if (cart == null) {
            return null;
        }

        List<CartItemDto> cartItemDtos = cart.getCartItemList().stream()
                .map(CartItemTransformer::cartItemToDto)
                .collect(Collectors.toList());

        double totalAmount = cartItemDtos.stream()
                .mapToDouble(CartItemDto::getTotalPrice)
                .sum();

        double originalAmount = cartItemDtos.stream()
                .mapToDouble(item -> item.getOriginalPrice() * item.getQuantity())
                .sum();

        double totalDiscount = originalAmount - totalAmount;

        int totalItems = cartItemDtos.stream()
                .mapToInt(CartItemDto::getQuantity)
                .sum();

        return CartResponseDto.builder()
                .cartId(cart.getId())
                .userName(cart.getUser().getUserName())
                .userEmail(cart.getUser().getEmail())
                .cartItems(cartItemDtos)
                .totalItems(totalItems)
                .totalAmount(totalAmount)
                .totalDiscount(totalDiscount)
                .originalAmount(originalAmount)
                .createdAt(cart.getCreatedAt())
                .updatedAt(cart.getUpdatedAt())
                .build();
    }
}