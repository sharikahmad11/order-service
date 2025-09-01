package com.sharik.order_service.dto;

import com.sharik.order_service.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
    private LocalDateTime orderDate;
    private List<Product> products;
}
