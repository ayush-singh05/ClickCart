package com.clickcart.ClickCart.dto.response;

import com.clickcart.ClickCart.Enum.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.AccessType;

import java.math.BigDecimal;
import java.util.Map;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderSummaryResponseDto {
    long totalOrders;
     BigDecimal totalRevenue;
     Map<OrderStatus, Long> ordersByStatus;
     BigDecimal averageOrderValue;
}
