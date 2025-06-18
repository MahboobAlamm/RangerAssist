package com.pickassist.pao.validator;

import com.pickassist.pao.dto.OrderDTO;
import com.pickassist.pao.exception.ValidationException;
import com.pickassist.pao.constants.ErrorMessages;
import com.pickassist.pao.utils.DataSanitizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator {

    @Autowired
    private DataSanitizer dataSanitizer;

    public void validate(OrderDTO dto) {
        
        if(dto.getStatus().equals("") || dto.getStatus() == null){
            throw new ValidationException(ErrorMessages.STATUS_REQUIRED);
        }
        if(dto.getNoOfOrdelineId() <= 0){
            throw new ValidationException(ErrorMessages.INVALID_LINE_ITEM_COUNT);
        }
        if(dto.getOrderDt() == null){
            throw new ValidationException(ErrorMessages.ORDER_DATE_REQUIRED);
        }

        dataSanitizer.sanitizeString(dto.getStatus());

    }
}
