package com.clickcart.ClickCart.repository;

import com.clickcart.ClickCart.model.Cart;
import com.clickcart.ClickCart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUser(User user);

}
