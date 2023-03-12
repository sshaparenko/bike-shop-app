package com.spring.bike.bikeshopapp.controller.user;

import com.spring.bike.bikeshopapp.common.ApiResponse;
import com.spring.bike.bikeshopapp.dto.product.CreateProductDTO;
import com.spring.bike.bikeshopapp.dto.order.OrderDetailsDTO;
import com.spring.bike.bikeshopapp.service.OrderingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/user")
@SecurityRequirement(name = "jwt")
@RequiredArgsConstructor
public class UserOrderController {
    private final OrderingService orderingService;
    @GetMapping("/order")
    public List<OrderDetailsDTO> listOrders(@RequestHeader (name = "Authorization") String token) {
        return orderingService.listOrders(token);
    }
    @PostMapping("/order/create")
    public ResponseEntity<ApiResponse> createOrder(@RequestHeader (name = "Authorization") String token, @Valid @RequestBody List<CreateProductDTO> createProductDTOS, BindingResult result) {
        if (result.hasErrors())
            return new ResponseEntity<>(new ApiResponse(false, "bad request"), HttpStatus.BAD_REQUEST);
        try {
            orderingService.createOrderWithProducts(token, createProductDTOS);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(new ApiResponse(false, "invalid list of products"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiResponse(true, "order was created successfully"), HttpStatus.CREATED);
    }
}