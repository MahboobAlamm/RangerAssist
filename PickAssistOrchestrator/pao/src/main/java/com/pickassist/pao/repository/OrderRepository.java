package com.pickassist.pao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pickassist.pao.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
}
