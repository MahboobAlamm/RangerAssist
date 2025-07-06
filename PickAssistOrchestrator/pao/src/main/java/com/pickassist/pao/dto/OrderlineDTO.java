package com.pickassist.pao.dto;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderlineDTO {

    private Long orderlineId;
    private String customerName;
    private String customerAddress;
    private String customerEmail;
    private String customerContactNumber;
    private int priority;
    private Timestamp orderlineDt;

    @JsonProperty("orderlineItems")
    private List<OrderlineItemDTO> orderlineItemDTO;
}
