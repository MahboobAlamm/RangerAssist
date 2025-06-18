package com.pickassist.pao.utils;

import org.springframework.stereotype.Component;

@Component
public class DataSanitizer {

    public String sanitizeString(String input){
        if(input == null) return null;
        return input.trim();
    }
}
