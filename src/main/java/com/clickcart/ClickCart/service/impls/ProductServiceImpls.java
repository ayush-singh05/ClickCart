package com.clickcart.ClickCart.service.impls;

import com.clickcart.ClickCart.dto.request.ProductRequestDto;
import com.clickcart.ClickCart.dto.response.ProductResponseDto;
import com.clickcart.ClickCart.exception.ProductNotFoundException;
import com.clickcart.ClickCart.model.Product;
import com.clickcart.ClickCart.repository.ProductRepository;
import com.clickcart.ClickCart.service.ProductService;
import com.clickcart.ClickCart.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<ProductResponseDto> getAllProduct() {

        List<Product> product = productRepository.findAll();

        List<ProductResponseDto> responseList = new ArrayList<>();

        for(Product product1 : product){
            responseList.add(ProductTransformer.productToProductResponseDto(product1));
        }
        return responseList;
    }

    @Override
    public ProductResponseDto updateProduct(int productId, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Invalid Product Id"));
        ProductTransformer.productRequestDtoToUpdateProduct(product, productRequestDto);
        Product saveProduct = productRepository.save(product);
        return ProductTransformer.productToProductResponseDto(saveProduct);

    }

    @Override
    public void deleteProduct(int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Invalid Product Id"));
                productRepository.delete(product);
    }

    @Override
    public List<ProductResponseDto> getProductCategory(String category) {

        List<Product> product = productRepository.findByCategory(category);
        List<ProductResponseDto> responseList = new ArrayList<>();

        for(Product product1 : product){
            responseList.add(ProductTransformer.productToProductResponseDto(product1));
        }

        return responseList;
    }

    @Override
    public List<ProductResponseDto> searchProduct(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            throw new IllegalArgumentException("Keyword cannot be empty");
        }
        List<Product> products = productRepository.searchProducts(keyword);
        List<ProductResponseDto> responseDtos = new ArrayList<>();
        for(Product pro : products){
            responseDtos.add(ProductTransformer.productToProductResponseDto(pro));
        }

        return responseDtos;
    }


}
