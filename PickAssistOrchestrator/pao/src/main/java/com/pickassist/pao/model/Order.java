package com.pickassist.pao.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="Orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderSK;

    @Column(unique=true)
    private Long orderId;
    private String status;
    private Timestamp orderDt;
    private Long noOfOrdelineId;
    private Timestamp timestamp;
    
    @Override
    public String toString() {
        return "Order{" +
            "orderSK=" + orderSK +
            "orderId=" + orderId +
            ", status='" + status + '\'' +
            ", noOfOrdelineId=" + noOfOrdelineId +
            ", orderDt=" + orderDt +
            ", timestamp=" + timestamp +
            '}';
        }
}
