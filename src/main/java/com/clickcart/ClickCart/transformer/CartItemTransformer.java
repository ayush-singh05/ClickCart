package com.clickcart.ClickCart.transformer;

import com.clickcart.ClickCart.dto.response.CartItemDto;
import com.clickcart.ClickCart.model.CartItem;
import com.clickcart.ClickCart.model.Product;



    public class CartItemTransformer {

        public static CartItemDto cartItemToDto(CartItem cartItem) {
            if (cartItem == null) {
                return null;
            }

            Product product = cartItem.getProduct();
            double totalPrice = cartItem.getQuantity() * cartItem.getPrice();
            boolean inStock = product.getAvailableQuantity() > 0;

            // Calculate discount if applicable
            Double originalPrice = product.getOriginalPrice() != null ?
                    product.getOriginalPrice() : product.getPrice();
            Double discount = calculateDiscount(originalPrice, product.getPrice());

            return CartItemDto.builder()
                    .cartItemId(cartItem.getId())
                    .productId(product.getProductId())
                    .productName(product.getProductName())
                    .productDescription(product.getProductDescription())
                    .productImage(product.getProductImage())
                    .productBrand(product.getBrand())
                    .productCategory(product.getCategory() != null ? product.getCategory().toString() : null)
                    .quantity(cartItem.getQuantity())
                    .unitPrice(cartItem.getPrice())
                    .originalPrice(originalPrice)
                    .discount(discount)
                    .totalPrice(totalPrice)
                    .availableStock(product.getAvailableQuantity())
                    .inStock(inStock)
                    .build();
        }

        private static Double calculateDiscount(Double originalPrice, Double currentPrice) {
            if (originalPrice == null || currentPrice == null || originalPrice <= currentPrice) {
                return 0.0;
            }
            return ((originalPrice - currentPrice) / originalPrice) * 100;
        }

}
