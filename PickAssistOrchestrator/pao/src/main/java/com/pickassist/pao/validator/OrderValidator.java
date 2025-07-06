package com.pickassist.pao.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pickassist.pao.constants.ErrorMessages;
import com.pickassist.pao.dto.OrderDTO;
import com.pickassist.pao.dto.OrderlineDTO;
import com.pickassist.pao.exception.ValidationException;
import com.pickassist.pao.utils.DataSanitizer;

@Component
public class OrderValidator {

    @Autowired
    private OrderlineValidator orderlineValidator;

    @Autowired
    private DataSanitizer dataSanitizer;

    public void validate(OrderDTO dto) {
        sanitizeFields(dto);
        validateOrderMetadata(dto);
        validateOrderlines(dto);
       
    }

    private void sanitizeFields(OrderDTO dto) {
        dto.setStatus(dataSanitizer.sanitizeString(dto.getStatus()));
    }

    private void validateOrderMetadata(OrderDTO dto) {

        if (dto.getOrderId() == null || dto.getOrderId() < 0) {
            throw new ValidationException(ErrorMessages.ORDER_ID_REQUIRED);
        }

        if (dto.getStatus() == null || dto.getStatus().isEmpty()) {
            throw new ValidationException(ErrorMessages.ORDER_STATUS_REQUIRED);
        }

        if (dto.getOrderDt() == null) {
            throw new ValidationException(ErrorMessages.ORDER_DT_REQUIRED);
        }

        if (dto.getOrderlineDTO() == null || dto.getOrderlineDTO().isEmpty()) {
            throw new ValidationException(ErrorMessages.ORDER_INVALID_LINE_ITEM_COUNT);
        }
    }

    private void validateOrderlines(OrderDTO dto) {
        if (dto.getOrderlineDTO() != null) {
            for (OrderlineDTO orderline : dto.getOrderlineDTO()) {
                orderlineValidator.validate(orderline);
            }
        }
    }
}
