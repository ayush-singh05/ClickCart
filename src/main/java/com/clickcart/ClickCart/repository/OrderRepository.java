package com.clickcart.ClickCart.repository;

import com.clickcart.ClickCart.Enum.OrderStatus;
import com.clickcart.ClickCart.model.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

//    Optional<Order> getOrderById(Long orderId);

    List<Order> findByUserIdAndStatus(String userId, OrderStatus status);

    List<Order> findByStatus(OrderStatus status);

    List<Order> findByUserId(String userId);
}
