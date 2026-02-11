package com.clickcart.ClickCart.service;

import com.clickcart.ClickCart.dto.request.ProductRequestDto;
import com.clickcart.ClickCart.dto.response.ProductResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto);
}
