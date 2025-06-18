package com.pickassist.pao.model;

import java.sql.Timestamp;

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
    private int orderId;
    private String status;
    private int noOfOrdelineId;
    private Timestamp orderDt;
    private Timestamp timestamp;
    
    @Override
    public String toString() {
        return "Order{" +
            "orderId=" + orderId +
            ", status='" + status + '\'' +
            ", noOfOrdelineId=" + noOfOrdelineId +
            ", orderDt=" + orderDt +
            ", timestamp=" + timestamp +
            '}';
        }
}
