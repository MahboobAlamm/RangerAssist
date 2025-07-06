package com.pickassist.pao.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerSK;

    @Column(unique=true)
    private Long orderlineId;
    private String customerName;
    private String customerAddress;
    private String customerEmail;
    private String customerContactNumber;
    private Long orderId;
    private int priority;
    private Timestamp orderLineDt;
    private Timestamp timestamp;

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerSK +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerContactNumber=" + customerContactNumber +
                ", orderId=" + orderId +
                ", priority=" + priority +
                ", orderlineId=" + orderlineId +
                ", orderLineDt=" + orderLineDt +
                ", timestamp=" + timestamp +
                '}';
    }
}
