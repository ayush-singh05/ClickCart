package com.clickcart.ClickCart.service;

import com.clickcart.ClickCart.dto.request.ProductRequestDto;
import com.clickcart.ClickCart.dto.response.ProductResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto);

    List<ProductResponseDto> getAllProduct();

    ProductResponseDto updateProduct(int productId, ProductRequestDto productRequestDto);


}
