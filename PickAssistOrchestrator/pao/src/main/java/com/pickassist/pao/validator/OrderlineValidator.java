package com.pickassist.pao.validator;

import com.pickassist.pao.dto.OrderlineDTO;
import com.pickassist.pao.exception.ValidationException;
import com.pickassist.pao.constants.ErrorMessages;
import com.pickassist.pao.constants.RegexPatterns;
import com.pickassist.pao.utils.DataSanitizer;
import com.pickassist.pao.dto.OrderlineItemDTO;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderlineValidator {

    @Autowired
    private DataSanitizer dataSanitizer;

    @Autowired
    private OrderlineItemValidator orderlineItemValidator;

    private static final Pattern EMAIL_PATTERN = Pattern.compile(RegexPatterns.EMAIL_REGEX_STRING);
    private static final Pattern PHONE_PATTERN = Pattern.compile(RegexPatterns.PHONE_REGEX_STRING);

    public void validate(OrderlineDTO dto) {
        sanitizeFields(dto);
        validateIdsAndPriority(dto);
        validateCustomerInfo(dto);
        validateEmail(dto.getCustomerEmail());
        validatePhone(dto.getCustomerContactNumber());
        validateOrderDate(dto);
        validateOrderlineItems(dto);
    }

    private void sanitizeFields(OrderlineDTO dto) {
        dto.setCustomerName(dataSanitizer.sanitizeString(dto.getCustomerName()));
        dto.setCustomerAddress(dataSanitizer.sanitizeString(dto.getCustomerAddress()));
        dto.setCustomerEmail(dataSanitizer.sanitizeString(dto.getCustomerEmail()));
        dto.setCustomerContactNumber(dataSanitizer.sanitizeString(dto.getCustomerContactNumber()));
    }

    private void validateIdsAndPriority(OrderlineDTO dto) {
        if (dto.getOrderlineId() == null || dto.getOrderlineId() < 0) {
            throw new ValidationException(ErrorMessages.ORDERLINE_ID_REQUIRED);
        }
        if (dto.getPriority() != 1 && dto.getPriority() != 2) {
            throw new ValidationException(ErrorMessages.ORDERLINE_INVALID_PRIORITY);
        }
    }

    private void validateCustomerInfo(OrderlineDTO dto) {
        if (dto.getCustomerName() == null || dto.getCustomerName().isEmpty()) {
            throw new ValidationException(ErrorMessages.ORDERLINE_CUSTOMER_NAME_REQUIRED);
        }
        if (dto.getCustomerAddress() == null || dto.getCustomerAddress().isEmpty()) {
            throw new ValidationException(ErrorMessages.ORDERLINE_CUSTOMER_ADDRESS_REQUIRED);
        }
        if (dto.getCustomerEmail() == null || dto.getCustomerEmail().isEmpty()) {
            throw new ValidationException(ErrorMessages.ORDERLINE_CUSTOMER_EMAIL_REQUIRED);
        }
        if (dto.getCustomerContactNumber() == null || dto.getCustomerContactNumber().isEmpty()) {
            throw new ValidationException(ErrorMessages.ORDERLINE_CUSTOMER_CONTACT_REQUIRED);
        }
    }

    private void validateEmail(String email) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new ValidationException(ErrorMessages.ORDERLINE_CUSTOMER_EMAIL_INVALID);
        }
    }

    private void validatePhone(String phone) {
        if (!PHONE_PATTERN.matcher(phone).matches()) {
            throw new ValidationException(ErrorMessages.ORDERLINE_CUSTOMER_CONTACT_INVALID);
        }
    }

    private void validateOrderDate(OrderlineDTO dto) {
        if (dto.getOrderlineDt() == null) {
            throw new ValidationException(ErrorMessages.ORDERLINE_DATE_REQUIRED);
        }
    }

    private void validateOrderlineItems(OrderlineDTO dto) {
        if (dto.getOrderlineItemDTO() != null) {
            for (OrderlineItemDTO item : dto.getOrderlineItemDTO()) {
                orderlineItemValidator.validate(item);
            }
        }
    }
}
