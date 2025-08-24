package com.pickassist.pao.client;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class WarehouseProductAvailability {

    private final RestTemplate restTemplate;
    private final String warehouseServiceUrl;

    public WarehouseProductAvailability(RestTemplate restTemplate, @Value("${warehouse.service.url}") String warehouseServiceUrl){
        this.restTemplate = restTemplate;
        this.warehouseServiceUrl = warehouseServiceUrl;
    }

    public List<Long> productAvailability(Map<Long, List<Map<String, Object>>> orderlinePrioritySortedAllProductList){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<Long, List<Map<String, Object>>>> request = new HttpEntity<>(orderlinePrioritySortedAllProductList, httpHeaders);
        try {
            ResponseEntity<List> response = restTemplate.postForEntity(warehouseServiceUrl, request, List.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Error calling warehouse service: {}", e.getMessage(), e);
            throw e;
        }
    }
}
