package com.pickassist.pao.dto;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class OrderDTO {

    private String status;
    private int noOfOrdelineId;
    private Timestamp orderDt;
    private Timestamp timestamp;
    
}
