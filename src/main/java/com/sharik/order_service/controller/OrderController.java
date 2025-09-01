package com.sharik.order_service.controller;

import com.sharik.order_service.dto.OrderDto;
import com.sharik.order_service.entity.Order;
import com.sharik.order_service.service.OrderService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@NoArgsConstructor
public class OrderController {
    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Order get(@PathVariable Long id) throws Exception {
        return orderService.get(id);
    }

    @PostMapping("/save")
    public Order save(@RequestBody Order order) {
        return orderService.save(order);
    }

    @PutMapping("/update")
    public Order update(@RequestBody Order order) {
        return orderService.update(order);
    }

    @PatchMapping("update/{id}")
    public Order updateById(@PathVariable Long id, @RequestBody OrderDto orderDto) {
        return orderService.updateById(id, orderDto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return orderService.delete(id);
    }

    @PostMapping("/saveAll")
    public String saveAll(@RequestBody List<Order> orderList){
        return orderService.saveOrders(orderList);
    }
    
}
