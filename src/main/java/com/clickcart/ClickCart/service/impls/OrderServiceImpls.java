package com.clickcart.ClickCart.service.impls;

import com.clickcart.ClickCart.Enum.OrderStatus;
import com.clickcart.ClickCart.dto.request.OrderRequestDto;
import com.clickcart.ClickCart.dto.response.OrderResponseDto;
import com.clickcart.ClickCart.dto.response.OrderSummaryResponseDto;
import com.clickcart.ClickCart.exception.InvalidOrderOperationException;
import com.clickcart.ClickCart.exception.OrderNotFoundException;
import com.clickcart.ClickCart.model.Order;
import com.clickcart.ClickCart.model.OrderItem;
import com.clickcart.ClickCart.model.User;
import com.clickcart.ClickCart.repository.CartItemRepository;
import com.clickcart.ClickCart.repository.CartRepository;
import com.clickcart.ClickCart.repository.OrderRepository;
import com.clickcart.ClickCart.repository.UserRepository;
import com.clickcart.ClickCart.service.OrderService;
import com.clickcart.ClickCart.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpls implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    CartRepository cartRepository;
    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {

        if (orderRequestDto.getItems() == null || orderRequestDto.getItems().isEmpty()) {
            throw new RuntimeException("Order must contain at least one item");
        }
        User user = userRepository.findById(orderRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        BigDecimal total = orderRequestDto.getItems().stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Order order = OrderTransformer.orderReqToOrder(orderRequestDto);
        order.setTotal(total);
        order.setUser(user);
        user.getCart().getCartItemList().clear();
        Order saveOrder = orderRepository.save(order);
        return OrderTransformer.orderToOrderResponse(saveOrder);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }

    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    public List<Order> getOrdersByUserIdAndStatus(String userId, OrderStatus status) {
        return orderRepository.findByUserIdAndStatus(userId, status);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));
    }

    @Transactional
    public Order updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = getOrderById(orderId);
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

    @Transactional
    public Order cancelOrder(Long orderId) {
        Order order = getOrderById(orderId);

        if (order.getStatus() == OrderStatus.SHIPPED || order.getStatus() == OrderStatus.DELIVERED) {
            throw new InvalidOrderOperationException(
                    "Cannot cancel an order that has been shipped or delivered");
        }

        order.setStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }

    public OrderSummaryResponseDto getOrderSummary() {
        List<Order> allOrders = orderRepository.findAll();

        long totalOrders = allOrders.size();
        BigDecimal totalRevenue = allOrders.stream()
                .map(Order::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<OrderStatus, Long> ordersByStatus = new HashMap<>();
        for (OrderStatus status : OrderStatus.values()) {
            ordersByStatus.put(status, allOrders.stream()
                    .filter(order -> order.getStatus() == status)
                    .count());
        }

        BigDecimal averageOrderValue = totalOrders > 0
                ? totalRevenue.divide(BigDecimal.valueOf(totalOrders), 2, BigDecimal.ROUND_HALF_UP)
                : BigDecimal.ZERO;

        return new OrderSummaryResponseDto(totalOrders, totalRevenue, ordersByStatus, averageOrderValue);
    }

}
