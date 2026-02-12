package com.clickcart.ClickCart.service.impls;

import com.clickcart.ClickCart.dto.response.CartResponseDto;
import com.clickcart.ClickCart.exception.CartItemNotFoundException;
import com.clickcart.ClickCart.exception.UserAlreadyExistsException;
import com.clickcart.ClickCart.model.Cart;
import com.clickcart.ClickCart.model.CartItem;
import com.clickcart.ClickCart.model.Product;
import com.clickcart.ClickCart.model.User;
import com.clickcart.ClickCart.repository.CartItemRepository;
import com.clickcart.ClickCart.repository.CartRepository;
import com.clickcart.ClickCart.repository.ProductRepository;
import com.clickcart.ClickCart.repository.UserRepository;
import com.clickcart.ClickCart.service.CartService;
import com.clickcart.ClickCart.transformer.CartTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartServiceImpls implements CartService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    ProductRepository productRepo;
    private static final Logger log = LoggerFactory.getLogger(CartService.class);

    @Override
    public CartResponseDto addToCart(int userId, int productId, Integer quantity) {
        try{
            User user = userRepository.findById(userId);
            Product product = productRepo.findById(productId);
//            Cart cart = cartRepository.findByUser(user);
            Cart cart = cartRepository.findByUser(user)
                    .orElseGet(() -> {
                        Cart newCart = Cart.builder()
                                .user(user)
                                .cartItemList(new ArrayList<>())
                                .build();
                        return cartRepository.save(newCart);
                    });
            // logger
//            log.info("user -> {}", user);
//            log.info("Cart -> {}", cart);
//            log.info("product -> {}", product);

            // check if product already exist in card
            for(CartItem item : cart.getCartItemList()){
                if(item.getProduct().getProductId() == productId ){
                    item.setQuantity(item.getQuantity()+quantity);
                    Cart saveCart = cartRepository.save(cart);
                    return CartTransformer.cartToCartResponseDto(saveCart);
                }

            }
            CartItem cartItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(quantity)
                    .price(product.getPrice())
                    .build();

            cart.getCartItemList().add(cartItem);
            Cart saveCart = cartRepository.save(cart);
            return CartTransformer.cartToCartResponseDto(saveCart);
        }catch (Exception e){
            throw new UserAlreadyExistsException(e.getMessage());
        }
    }

    @Override
    public Cart getCartByUser(int userId) {
        return null;
    }

    @Override
    public void removeItem(int cartItemId) {
            CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new CartItemNotFoundException("Cart is Not found with id"+ cartItemId));
            cartItemRepository.delete(cartItem);
        log.info("Cart item {} removed successfully", cartItemId);

    }
}
