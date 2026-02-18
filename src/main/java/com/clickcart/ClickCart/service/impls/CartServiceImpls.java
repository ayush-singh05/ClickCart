package com.clickcart.ClickCart.service.impls;

import com.clickcart.ClickCart.dto.response.CartResponseDto;
import com.clickcart.ClickCart.exception.CartItemNotFoundException;
import com.clickcart.ClickCart.exception.InsufficientStockException;
import com.clickcart.ClickCart.exception.ProductNotFoundException;
import com.clickcart.ClickCart.exception.UserNotFoundException;
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
import java.util.List;
import java.util.Optional;

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

            log.info("User Details {}", userId, productId,quantity);
            User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found"));
            Product product = productRepo.findById(productId).orElseThrow(() -> new ProductNotFoundException("Invalid Product Id"));
            if(product.getAvailableQuantity() < quantity){
                throw new ProductNotFoundException("Insufficient Product Quantity available");
            }
            // Get cart or create
            Cart cart = createOrGetCart(user);

            log.info("Created Cart {}",cart);
            // check for product already available in cart
            Optional<CartItem> existingCartItem = cart.getCartItemList()
                    .stream().filter(item -> item.getProduct().getProductId().equals(productId)).findFirst();

            if(existingCartItem.isPresent()){
                CartItem cartItem = existingCartItem.get();
                int newQty = cartItem.getQuantity() + quantity;

                // validate quantity
                if(product.getAvailableQuantity() < newQty){
                    throw new ProductNotFoundException("Insufficient Product Quantity available");
                }
                cartItem.setQuantity(newQty);
                log.info("Updated cart item quantity to {}", newQty);
            }else {
                // Add new Cart
                CartItem cartItem = CartItem.builder()
                        .product(product)
                        .cart(cart)
                        .quantity(quantity)
                        .price(product.getPrice())
                        .build();
                cart.getCartItemList().add(cartItem);
                log.info("Added new cart item for product {}", productId);
            }
            Cart saveCart = cartRepository.save(cart);
            return CartTransformer.cartToCartResponseDto(saveCart);


    }

    @Override
    public CartResponseDto getCartByUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new CartItemNotFoundException("Cart Not Found "));
        return CartTransformer.cartToCartResponseDto(cart);
    }

    @Override
    public void removeItem(int cartItemId) {
            CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new CartItemNotFoundException("Cart is Not found with id"+ cartItemId));
            cartItemRepository.delete(cartItem);
        log.info("Cart item {} removed successfully", cartItemId);

    }

    @Override
    public CartResponseDto updateCart(int cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new CartItemNotFoundException("Cart Not Found"));
        Product product = productRepo.findById(cartItem.getProduct().getProductId()).orElseThrow(() -> new ProductNotFoundException("Product Not Found"));

        if(product.getAvailableQuantity() < cartItem.getQuantity()+quantity){
            throw new InsufficientStockException("Insufficient Stock Quantity");
        }
        cartItem.setQuantity(quantity);

        cartItemRepository.save(cartItem);
        Cart cart = cartItem.getCart();
        return CartTransformer.cartToCartResponseDto(cart);
    }

    @Override
    public void clearCart(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new UserNotFoundException("Invalid user id "+ userId));
        cart.getCartItemList().clear();
        cartRepository.save(cart);
    }

    /* Helper function for create a new cart or get existing cart */
    public Cart createOrGetCart(User user){
        return cartRepository.findByUser(user).orElseGet(() -> {
            log.info("Creating a new Cart {}", user.getId());
            Cart newCart = Cart.builder()
                    .user(user)
                    .cartItemList(new ArrayList<>())
                    .build();
            log.info("new cart created successfully newCart={}", newCart);
            return cartRepository.save(newCart);
        });
    }
}
