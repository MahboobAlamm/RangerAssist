package com.pickassist.pao.validator;

import com.pickassist.pao.constants.ErrorMessages;
import com.pickassist.pao.dto.OrderlineItemDTO;
import com.pickassist.pao.exception.ValidationException;
import com.pickassist.pao.utils.DataSanitizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderlineItemValidator {

    @Autowired
    private DataSanitizer dataSanitizer;

    public void validate(OrderlineItemDTO dto) {

        if (dto.getOrderlineItemId() == null || dto.getOrderlineItemId() < 0) {
            throw new ValidationException(ErrorMessages.ORDERLINEITEM_ID_REQUIRED);
        }

        dto.setProductName(dataSanitizer.sanitizeString(dto.getProductName()));
        dto.setProductBrand(dataSanitizer.sanitizeString(dto.getProductBrand()));
        dto.setOrderlineItemStatus(dataSanitizer.sanitizeString(dto.getOrderlineItemStatus()));

        if (dto.getOrderlineItemStatus() == null || dto.getOrderlineItemStatus().isEmpty()) {
            throw new ValidationException(ErrorMessages.ORDERLINEITEM_STATUS_REQUIRED);
        }

        if (dto.getProductName() == null || dto.getProductName().isEmpty()) {
            throw new ValidationException(ErrorMessages.ORDERLINEITEM_PRODUCT_NAME_REQUIRED);
        }

        if (dto.getProductQty() == null || dto.getProductQty() <= 0) {
            throw new ValidationException(ErrorMessages.ORDERLINEITEM_PRODUCT_QTY_INVALID);
        }

        if (dto.getProductBrand() == null || dto.getProductBrand().isEmpty()) {
            throw new ValidationException(ErrorMessages.ORDERLINEITEM_PRODUCT_BRAND_REQUIRED);
        }
    }
}
