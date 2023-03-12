package com.spring.bike.bikeshopapp.controller.admin;

import com.spring.bike.bikeshopapp.model.Order;
import com.spring.bike.bikeshopapp.model.User;
import com.spring.bike.bikeshopapp.dto.order.OrderDTO;
import com.spring.bike.bikeshopapp.service.OrderBaseService;
import com.spring.bike.bikeshopapp.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * todo:
 * delete
 * update
 */
@RestController
@RequestMapping("/api/v1/admin")
@SecurityRequirement(name = "jwt")
@RequiredArgsConstructor
public class AdminOrderController {
    private final OrderBaseService orderBaseService;
    private final UserService userService;

    @GetMapping("/orders")
    public List<OrderDTO> allOrders() {
        return orderBaseService.listOrders();
    }

    @GetMapping("/order/{id}")
    public OrderDTO getOrder(@PathVariable Long id) {
        Optional<Order> orderOptional = orderBaseService.readOrder(id);
        if (orderOptional.isEmpty()) return new OrderDTO();
        return new OrderDTO(orderOptional.get());
    }

    @GetMapping("/order/user/{username}")
    public List<OrderDTO> ordersByUsername(@PathVariable String username) {
        Optional<User> userOptional = userService.readUser(username);
        if (userOptional.isEmpty()) return new ArrayList<>();
        return orderBaseService.getOrdersOfUser(userOptional.get().getId());
    }

}
