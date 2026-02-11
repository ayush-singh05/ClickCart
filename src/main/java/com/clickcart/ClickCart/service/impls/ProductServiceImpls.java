package com.clickcart.ClickCart.service.impls;

import com.clickcart.ClickCart.dto.request.ProductRequestDto;
import com.clickcart.ClickCart.dto.response.ProductResponseDto;
import com.clickcart.ClickCart.model.Product;
import com.clickcart.ClickCart.repository.ProductRepository;
import com.clickcart.ClickCart.service.ProductService;
import com.clickcart.ClickCart.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpls implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        Product product = ProductTransformer.productRequestDtoToProduct(productRequestDto);
        productRepository.save(product);
        ProductResponseDto productResponseDto = ProductTransformer.productToProductResponseDto(product);

        return productResponseDto;

    }
}
