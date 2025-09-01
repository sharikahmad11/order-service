package com.sharik.order_service.service;

import com.sharik.order_service.dto.OrderDto;
import com.sharik.order_service.entity.Order;
import com.sharik.order_service.exception.OrderNotFoundException;
import com.sharik.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class OrderService {
    OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders;
    }

    public Order get(Long id) throws Exception {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order Not Found!!!"));
    }

    public Order save(Order Order) {
        return orderRepository.save(Order);
    }

    public Order update(Order order) {
        if(null!=order.getOrderId()) {
            Order existingOrder = orderRepository.findById(order.getOrderId()).orElseThrow(() -> new OrderNotFoundException("Order Not Found!!!"));
        } else{
            throw new OrderNotFoundException("Order Not Found!!!");
        }
        Order updatedOrder = new Order();
        updatedOrder.setOrderDate(order.getOrderDate());
        updatedOrder.setProducts(order.getProducts());
        return orderRepository.save(updatedOrder);
    }

    public Order updateById(Long id, OrderDto orderDto) {
        Order existingOrder = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order Not Found!!!"));
        Order newOrder = new Order();
        if(null!=orderDto.getOrderDate())
            existingOrder.setOrderDate(existingOrder.getOrderDate());
        if(null!=orderDto.getProducts())
            existingOrder.setProducts(existingOrder.getProducts());

        return orderRepository.save(existingOrder);
    }

    public String delete(Long id) {
        Order Order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order Not Found!!!"));
        if(null!=Order){
            orderRepository.delete(Order);
            return "Order deleted successfully!!!";
        }
        return "Order Not Found!!!";
    }

    public String saveOrders(List<Order> OrderList) {
        Instant start = Instant.now();
        System.out.printf("Order Data addition to DB started : " + start);
        orderRepository.saveAll(OrderList);
        Instant end = Instant.now();
        System.out.printf("Order Data addition to DB completed : " + end);
        System.out.printf("Total time taken: " + Duration.between(end,start));
        return "Orders data added to Database";
    }
}
