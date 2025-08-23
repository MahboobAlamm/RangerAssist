package com.pickassist.pao.dto;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long orderId;
    private String orderStatus;
    private Timestamp orderDt;
    private Long noOfOrderlineId;

    @JsonProperty("orderline")
    private List<OrderlineDTO> orderlineDTO;
}
