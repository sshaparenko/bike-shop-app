package com.spring.bike.bikeshopapp.service;

import com.spring.bike.bikeshopapp.dto.product.CreateProductDTO;
import com.spring.bike.bikeshopapp.dto.order.OrderDetailsDTO;
import com.spring.bike.bikeshopapp.model.Category;
import com.spring.bike.bikeshopapp.model.Order;
import com.spring.bike.bikeshopapp.model.Product;
import com.spring.bike.bikeshopapp.model.User;
import com.spring.bike.bikeshopapp.repository.CategoryRepository;
import com.spring.bike.bikeshopapp.repository.OrderRepository;
import com.spring.bike.bikeshopapp.repository.UserRepository;
import com.spring.bike.bikeshopapp.security.config.JwtService;
import com.spring.bike.bikeshopapp.service.mappers.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderingService {
    private final OrderRepository orderRepository;
    private final CategoryRepository categoryRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final ProductMapper mapper;

    public List<OrderDetailsDTO> listOrders(String token) {
        Optional<User> user = userFromToken(token);
        if (user.isEmpty()) return new ArrayList<>();
        Long id = user.get().getId();
        return orderRepository.findAllByUserId(id).stream().map(OrderDetailsDTO::new).collect(Collectors.toList());
    }

    public void createOrderWithProducts(String token, List<CreateProductDTO> createProductDTOS) {
        Optional<User> user = userFromToken(token);
        if (user.isEmpty()) return;
        Set<Product> productSet = createProductDTOS.stream().map(p -> {
            Category category = categoryRepository.findById(p.getCategoryId()).orElseThrow(NoSuchElementException::new);
            return mapper.DTOtoProduct(p, category);
        }).collect(Collectors.toSet());
        createOrder(user.get(), productSet);
    }

    public void createOrder(User user, Set<Product> products) {
        Order order = new Order();
        order.setCreationDate(Date.valueOf(LocalDate.now()));
        order.setPrice(0);
        order.setUser(user);
        order.setProducts(products);
        orderRepository.save(order);
    }

    private Optional<User> userFromToken(String token) {
        String jwtToken = token.substring(7);
        String username = jwtService.extractUsername(jwtToken);
        return userRepository.findByLoginName(username);
    }
}
