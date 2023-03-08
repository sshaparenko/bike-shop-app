package com.spring.bike.bikeshopapp.controller.user;

import com.spring.bike.bikeshopapp.entity.Order;
import com.spring.bike.bikeshopapp.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@SecurityRequirement(name = "jwt")
@RequiredArgsConstructor
public class UserOrderController {
    private final OrderService orderService;
    @GetMapping("/order")
    public List<Order> listOrders(@RequestHeader (name = "Authorization") String token) {
        return orderService.listOrders(token);
    }
}