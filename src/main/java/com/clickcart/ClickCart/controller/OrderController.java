package com.clickcart.ClickCart.controller;

import com.clickcart.ClickCart.Enum.OrderStatus;
import com.clickcart.ClickCart.dto.request.OrderRequestDto;
import com.clickcart.ClickCart.dto.request.StatusUpdateRequestDto;
import com.clickcart.ClickCart.dto.response.OrderResponseDto;
import com.clickcart.ClickCart.dto.response.OrderSummaryResponseDto;
import com.clickcart.ClickCart.model.Order;
import com.clickcart.ClickCart.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity createOrder(@RequestBody OrderRequestDto orderRequestDto){
        OrderResponseDto orderResponseDto = orderService.createOrder(orderRequestDto);

        return ResponseEntity.ok(orderResponseDto);
    }
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllOrders(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) OrderStatus status) {

        List<Order> orders;

        if (userId != null && status != null) {
            orders = orderService.getOrdersByUserIdAndStatus(userId, status);
        } else if (userId != null) {
            orders = orderService.getOrdersByUserId(userId);
        } else if (status != null) {
            orders = orderService.getOrdersByStatus(status);
        } else {
            orders = orderService.getAllOrders();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("count", orders.size());
        response.put("orders", orders);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Map<String, Object>> getOrderById(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);

        Map<String, Object> response = new HashMap<>();
        response.put("order", order);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{orderId}/status")
    public ResponseEntity<Map<String, Object>> updateOrderStatus(
            @PathVariable Long orderId,
            @Valid @RequestBody StatusUpdateRequestDto statusUpdateRequest) {

        Order order = orderService.updateOrderStatus(orderId, statusUpdateRequest.getOrderStatus());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Order status updated successfully");
        response.put("order", order);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Map<String, Object>> cancelOrder(@PathVariable Long orderId) {
        Order order = orderService.cancelOrder(orderId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Order cancelled successfully");
        response.put("order", order);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/analytics/summary")
    public ResponseEntity<Map<String, Object>> getOrderSummary() {
        OrderSummaryResponseDto summary = orderService.getOrderSummary();

        Map<String, Object> response = new HashMap<>();
        response.put("summary", summary);

        return ResponseEntity.ok(response);
    }
}
