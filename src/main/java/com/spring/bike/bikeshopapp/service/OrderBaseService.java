package com.spring.bike.bikeshopapp.service;


import com.spring.bike.bikeshopapp.dto.order.UpdateOrderDTO;
import com.spring.bike.bikeshopapp.model.Order;
import com.spring.bike.bikeshopapp.model.User;
import com.spring.bike.bikeshopapp.dto.order.OrderDTO;
import com.spring.bike.bikeshopapp.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.sql.Date;

//todo add, delete, update order
@Service
@RequiredArgsConstructor
public class OrderBaseService {
    private final OrderRepository orderRepository;

    public List<OrderDTO> listOrders() {
        return orderRepository.findAll().stream().map(o -> new OrderDTO(o)).collect(Collectors.toList());
    }

    public List<OrderDTO> getOrdersOfUser(Long userId) {
        return orderRepository.findAllByUserId(userId).stream().map(o -> new OrderDTO(o)).collect(Collectors.toList());
    }

    public Optional<Order> readOrder(Long id) {
        return orderRepository.findById(id);
    }

    private Order DTOtoOrder(OrderDTO orderDTO, User user) {
        Order order = new Order();
        order.setCreationDate(orderDTO.getCreationDate());
        order.setDeliveryDate(orderDTO.getDeliveryDate());
        order.setPrice(orderDTO.getPrice());
        order.setUser(user);
        return order;
    }

    public void updateOrder(Order order, UpdateOrderDTO dto) {
        Date creationDate = dto.getCreationDate();
        Date deliveryDate = dto.getDeliveryDate();
        Integer price = dto.getPrice();
        if (creationDate != null)
            order.setCreationDate(creationDate);
        if(deliveryDate != null)
            order.setDeliveryDate(deliveryDate);
        if (price != null)
            order.setPrice(price);
        orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}