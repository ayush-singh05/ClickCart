package com.clickcart.ClickCart.transformer;

import com.clickcart.ClickCart.dto.request.ProductRequestDto;
import com.clickcart.ClickCart.dto.response.ProductResponseDto;
import com.clickcart.ClickCart.model.Product;
import com.clickcart.ClickCart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductTransformer {

    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .productName(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .productDescription(productRequestDto.getProductDescription())
                .active(productRequestDto.isActive())
                .originalPrice(productRequestDto.getOriginalPrice())
                .stockQuantity(productRequestDto.getStockQuantity())
                .category(productRequestDto.getCategory())
                .brand(productRequestDto.getBrand())
                .productImage(productRequestDto.getProductImage())
                .availableQuantity(productRequestDto.getAvailableQuantity())
                .productImage(productRequestDto.getProductImage())
                .build();
    }

    public static ProductResponseDto productToProductResponseDto(Product product) {
            ProductResponseDto productResponseDto = ProductResponseDto.builder()
                    .productName(product.getProductName())
                    .description(product.getProductDescription())
                    .brand(product.getBrand())
                    .price(product.getPrice())
                    .originalPrice(product.getOriginalPrice())
                    .category(product.getCategory())
                    .updateAt(product.getUpdatedAt())
                    .createdAt(product.getCreatedAt())
                    .availableQuantity(product.getAvailableQuantity())
                    .stockQuantity(product.getStockQuantity())
                    .build();
            return productResponseDto;
    }
}
