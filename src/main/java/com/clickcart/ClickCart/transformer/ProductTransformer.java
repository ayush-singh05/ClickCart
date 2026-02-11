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
                .productDescription(productRequestDto.getDescription())
                .active(productRequestDto.isActive())
                .stockQuantity(productRequestDto.getStockQuantity())
                .category(productRequestDto.getCategory())
                .productImage(productRequestDto.getProductImage())
                .build();
    }

    public static ProductResponseDto productToProductResponseDto(Product product) {
            ProductResponseDto productResponseDto = ProductResponseDto.builder()
                    .productName(product.getProductName())
                    .description(product.getProductDescription())
                    .category(product.getCategory())
                    .stockQuantity(product.getStockQuantity())
                    .build();
            return productResponseDto;
    }
}
