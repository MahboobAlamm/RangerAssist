package com.pickassist.pao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pickassist.pao.dto.OrderDTO;
import com.pickassist.pao.service.OrderService;
import com.pickassist.pao.validator.OrderValidator;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
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

    @PostMapping("/usersorders")
    public ResponseEntity<String> postMethodName(@RequestBody OrderDTO dto) {
        log.info("*******************************Users Orders Details Recived: " + dto);
        orderValidator.validate(dto);
        orderService.checkAvailabilityOfOrderlineItems(dto);

        return ResponseEntity.ok("save");
    }
    
}
