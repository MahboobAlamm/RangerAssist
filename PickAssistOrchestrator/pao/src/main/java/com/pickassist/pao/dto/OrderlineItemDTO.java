package com.pickassist.pao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderlineItemDTO {

    private Long orderlineItemId;
    private String productName;
    private Long productQty;
    private String productBrand;
}
