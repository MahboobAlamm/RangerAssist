package com.pickassist.pao.controller;

import com.pickassist.pao.dto.OrderDTO;
import com.pickassist.pao.validator.OrderValidator;
import com.pickassist.pao.service.OrderService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class OrderController {

    @Autowired
    private OrderValidator orderValidator;

    @Autowired
    private OrderService orderService;

    //TBD: Remove when project will be ready
    @GetMapping("/")
    public String requestMethodName() {
        return "Home API hit on 8080: Project is up and running.";
    }
    
    @PostMapping("/orders")
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO dto) {

        orderValidator.validate(dto);
        orderService.saveOrder(dto);

        return ResponseEntity.ok("Order saved successfully");
    }
}
