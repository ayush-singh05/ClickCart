package com.clickcart.ClickCart.controller;

import com.clickcart.ClickCart.dto.request.ProductRequestDto;
import com.clickcart.ClickCart.dto.response.ProductResponseDto;
import com.clickcart.ClickCart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/addProduct")
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto productRequestDto){
        try{
            ProductResponseDto productResponseDto= productService.addProduct(productRequestDto);
            return new ResponseEntity<>(productResponseDto,HttpStatus.CREATED);
        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>((HttpHeaders)null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllProduct(){

        List<ProductResponseDto> productList = productService.getAllProduct();
        return ResponseEntity.ok(productList);

    }
    @PutMapping("/update")
    public ResponseEntity updateProduct(@RequestParam int productId, @RequestBody ProductRequestDto productRequestDto){

        ProductResponseDto productResponseDto = productService.updateProduct(productId, productRequestDto);
        return ResponseEntity.ok(productResponseDto);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity deleteProduct(@PathVariable int productId){
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product Delete Successfully");

    }
    @GetMapping("/category/{category}")
    public ResponseEntity getProductCategory(@PathVariable String category) {
        List<ProductResponseDto> response = productService.getProductCategory(category);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity searchProduct(@RequestParam String keyword){
        List<ProductResponseDto> response = productService.searchProduct(keyword);
        return ResponseEntity.ok(response);
    }
}
