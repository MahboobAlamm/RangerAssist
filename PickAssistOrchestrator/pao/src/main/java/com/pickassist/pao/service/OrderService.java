package com.pickassist.pao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pickassist.pao.dto.OrderDTO;
import com.pickassist.pao.model.Order;
import com.pickassist.pao.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRespository;

    public void saveOrder(OrderDTO dto) {
        
    }
}
