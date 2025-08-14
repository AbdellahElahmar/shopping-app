package com.net.ElAhmar.order_service.controller;


import com.net.ElAhmar.order_service.dto.OrderRequest;
import com.net.ElAhmar.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest request) {
        orderService.placeOrder(request);
        return "Order placed successfuly...";
    }
}
