package com.clickcart.ClickCart.transformer;

import com.clickcart.ClickCart.Enum.OrderStatus;
import com.clickcart.ClickCart.dto.request.OrderRequestDto;
import com.clickcart.ClickCart.dto.response.OrderResponseDto;
import com.clickcart.ClickCart.model.Cart;
import com.clickcart.ClickCart.model.CartItem;
import com.clickcart.ClickCart.model.Order;
import com.clickcart.ClickCart.model.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public class OrderTransformer {

    public static OrderResponseDto orderToOrderResponse(Order order){
        return OrderResponseDto.builder()
                .user(order.getUser())
                .orderId(order.getOrderId())
                .amount(order.getTotal())
                .status(order.getStatus())
                .build();
//        return ;
    }
    public static Order orderReqToOrder(OrderRequestDto orderRequestDto){
        List<OrderItem> items = orderRequestDto.getItems();

        BigDecimal total = items.stream()
                .map(item -> item.getTotalPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Order order = Order.builder()
                .status(OrderStatus.PENDING)
                .items(items)
                .shippingAddress(orderRequestDto.getShippingAddress())
                .paymentMethod(orderRequestDto.getPaymentMethod() != null ? orderRequestDto.getPaymentMethod() : "Card")
                .total(total)
                .build();

        // VERY IMPORTANT: set order reference
        items.forEach(item -> item.setOrder(order));

        return order;
    }
}
