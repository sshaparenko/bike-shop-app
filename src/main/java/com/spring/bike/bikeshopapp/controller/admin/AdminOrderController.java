package com.spring.bike.bikeshopapp.controller.admin;

import com.spring.bike.bikeshopapp.common.ApiResponse;
import com.spring.bike.bikeshopapp.dto.order.UpdateOrderDTO;
import com.spring.bike.bikeshopapp.model.Order;
import com.spring.bike.bikeshopapp.model.User;
import com.spring.bike.bikeshopapp.dto.order.OrderDTO;
import com.spring.bike.bikeshopapp.service.OrderBaseService;
import com.spring.bike.bikeshopapp.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * todo: delete, update
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

    @PatchMapping("/order/update/{id}")
    public ResponseEntity<ApiResponse> updateOrder(@PathVariable Long id, @Valid @RequestBody UpdateOrderDTO dto, BindingResult result) {
        if (result.hasErrors()) new ResponseEntity<>(new ApiResponse(false, "bad request"), HttpStatus.BAD_REQUEST);
        Optional<Order> orderOptional = orderBaseService.readOrder(id);
        if (orderOptional.isEmpty()) return new ResponseEntity<>(new ApiResponse(false, "invalid id"), HttpStatus.NOT_FOUND);
        orderBaseService.updateOrder(orderOptional.get(), dto);
        return new ResponseEntity<>(new ApiResponse(true, "order was updated successfully"), HttpStatus.OK);
    }

    @DeleteMapping("/order/delete/{id}")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable Long id) {
        Optional<Order> orderOptional = orderBaseService.readOrder(id);
        if (orderOptional.isEmpty()) return new ResponseEntity<>(new ApiResponse(false, "invalid id"), HttpStatus.NOT_FOUND);
        orderBaseService.deleteOrder(id);
        return new ResponseEntity<>(new ApiResponse(true, "order was deleted successfully"), HttpStatus.OK);
    }

}
