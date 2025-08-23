package com.pickassist.pao.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pickassist.pao.dto.OrderDTO;
import com.pickassist.pao.dto.OrderlineDTO;
import com.pickassist.pao.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRespository;

    public void checkAvailabilityOfOrderlineItems(OrderDTO dto){
        List<OrderlineDTO> prioritySortedOrderline = dto.getOrderlineDTO()
                                                        .stream()
                                                        .sorted(Comparator.comparingInt(OrderlineDTO::getPriority))
                                                        .toList();
        log.info("**************************** PRIORITY SORTED ORDDER LIST: " + prioritySortedOrderline);

        Map<Long, List<Map<String, Object>>> orderLineAvailabilityList = new LinkedHashMap<>();

        for(OrderlineDTO sortedOrderline: prioritySortedOrderline){
            Long orderlineId = sortedOrderline.getOrderlineId();

            List<Map<String, Object>> orderlineItemList = sortedOrderline.getOrderlineItemDTO()
                                                            .stream()
                                                            .map(item -> {
                                                                Map<String, Object> orderlineItemData = new HashMap<>();
                                                                orderlineItemData.put("orderlineItemId", item.getOrderlineItemId());
                                                                orderlineItemData.put("productName", item.getProductName());
                                                                orderlineItemData.put("productQty", item.getProductQty());
                                                                return orderlineItemData;
                                                            }) 
                                                            .toList();

            orderLineAvailabilityList.put(orderlineId, orderlineItemList);
        }
        log.info("**************************** FINAL LIST SND TO CHECK AVAILABILITY: " + orderLineAvailabilityList);
    }

    //TBR
    public void saveOrder(OrderDTO dto) {
        
    }
}
