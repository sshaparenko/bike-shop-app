package com.spring.bike.bikeshopapp.service;

import com.spring.bike.bikeshopapp.entity.Order;
import com.spring.bike.bikeshopapp.entity.User;
import com.spring.bike.bikeshopapp.model.OrderDTO;
import com.spring.bike.bikeshopapp.repository.OrderRepository;
import com.spring.bike.bikeshopapp.repository.UserRepository;
import com.spring.bike.bikeshopapp.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    public List<OrderDTO> listOrders() {
        return orderRepository.findAll().stream().map(o -> new OrderDTO(o)).collect(Collectors.toList());
    }
    public List<Order> listOrders(String token) {
        String jwtToken = token.substring(7);
        String username = jwtService.extractUsername(jwtToken);
        Optional<User> user = userRepository.findByLoginName(username);
        if (user.isEmpty()) return new ArrayList<>();
        Long id = user.get().getId();
        System.out.println("ID: " + id);
        return orderRepository.findAllByUserId(id);
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
}