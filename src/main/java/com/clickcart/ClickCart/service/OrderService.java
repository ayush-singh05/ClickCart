package com.clickcart.ClickCart.service;

import com.clickcart.ClickCart.Enum.OrderStatus;
import com.clickcart.ClickCart.dto.request.OrderRequestDto;
import com.clickcart.ClickCart.dto.response.OrderResponseDto;
import com.clickcart.ClickCart.dto.response.OrderSummaryResponseDto;
import com.clickcart.ClickCart.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto);

    List<Order> getOrdersByUserIdAndStatus(String userId, OrderStatus status);

    List<Order> getOrdersByUserId(String userId);

    List<Order> getOrdersByStatus(OrderStatus status);

    List<Order> getAllOrders();

    OrderSummaryResponseDto getOrderSummary();

    Order cancelOrder(Long orderId);

    Order getOrderById(Long orderId);

    Order updateOrderStatus(Long orderId, OrderStatus newStatus);
}
