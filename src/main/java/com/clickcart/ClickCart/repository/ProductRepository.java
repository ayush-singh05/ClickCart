package com.clickcart.ClickCart.repository;

import com.clickcart.ClickCart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Product findById(int id);
}
