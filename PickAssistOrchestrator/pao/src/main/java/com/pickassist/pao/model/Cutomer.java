package com.pickassist.pao.model;

import java.sql.Timestamp;
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
public class Cutomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerEmail;
    private Double customerContactNumber;
    private int orderId;
    private char priority;
    private int orderlineId;
    private Timestamp orderLineDt;
    private Timestamp timestamp;

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
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
