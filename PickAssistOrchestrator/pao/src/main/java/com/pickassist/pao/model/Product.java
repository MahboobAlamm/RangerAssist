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
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int productSK;

    @Column(unique=true)
    private Long orderlineItemId;
    private Long ordelineId;
    private String orderlineItemStatus;
    private String productName;
    private Long productQty;
    private String brand;
    private Timestamp timestamp;

    @Override
    public String toString() {
        return "Product{" +
                "productSK=" + productSK +
                ", orderlineItemId=" + orderlineItemId +
                ", orderlineItemStatus=" + orderlineItemStatus +
                ", ordelineId=" + ordelineId +
                ", productName='" + productName + '\'' +
                ", productQty=" + productQty +
                ", brand='" + brand + '\'' +
                ", timestamp=" + timestamp +
            '}';
    }
}
