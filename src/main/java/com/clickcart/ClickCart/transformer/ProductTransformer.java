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
                .active(productRequestDto.getActive())
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
                    .productId(product.getProductId())
                    .productName(product.getProductName())
                    .description(product.getProductDescription())
                    .brand(product.getBrand())
                    .price(product.getPrice())
                    .active(product.getActive())
                    .originalPrice(product.getOriginalPrice())
                    .category(product.getCategory())
                    .updateAt(product.getUpdatedAt())
                    .createdAt(product.getCreatedAt())
                    .availableQuantity(product.getAvailableQuantity())
                    .stockQuantity(product.getStockQuantity())
                    .build();
            return productResponseDto;
    }

    public static void productRequestDtoToUpdateProduct(Product product, ProductRequestDto dto){
        if(dto.getBrand() != null){
            product.setBrand(dto.getBrand());
        }
        if(dto.getProductName() != null){
            product.setProductName(dto.getProductName());
        }
        if(dto.getProductDescription() != null){
            product.setProductDescription(dto.getProductDescription());
        }
        if(dto.getAvailableQuantity() != null){
            product.setAvailableQuantity(dto.getAvailableQuantity());
        }
        if(dto.getCategory() != null){
            product.setCategory(dto.getCategory());
        }
        if(dto.getPrice() != null){
            product.setPrice(dto.getPrice());
        }
        if(dto.getStockQuantity() != null){
            product.setStockQuantity(dto.getStockQuantity());
        }
        if(dto.getOriginalPrice() != null){
            product.setOriginalPrice(dto.getOriginalPrice());
        }
    }
}
