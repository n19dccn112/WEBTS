package com.nhom10.webts.model.dto;

import com.nhom10.webts.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusClass {
    Long id;
    OrderStatus orderStatus;
    BigDecimal amountOrderStatus;
}
