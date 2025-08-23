package com.pickassist.pao.constants;


public class ErrorMessages {

    private ErrorMessages() {
        throw new UnsupportedOperationException("ErrorMessageClass: Does not meant for initialization");
    }

    public static final String ORDER_ID_REQUIRED = "Valid Order Id is required";
    public static final String ORDER_STATUS_REQUIRED = "Order Status is required";
    public static final String ORDER_DT_REQUIRED = "Order Date is requred";
    public static final String ORDER_INVALID_LINE_ITEM_COUNT = "Order must have atleast one orderline to be placed";
    
    public static final String ORDERLINE_ID_REQUIRED = "Valid Orderline Id is required";
    public static final String ORDERLINE_INVALID_PRIORITY = "Priority can either be \"1\" for high or \"2\" for low";
    public static final String ORDERLINE_STATUS_REQUIRED = "Orderline Status is required";
    public static final String ORDERLINE_CUSTOMER_NAME_REQUIRED = "Customer Name is required";
    public static final String ORDERLINE_CUSTOMER_ADDRESS_REQUIRED = "Customer Address is required";
    public static final String ORDERLINE_CUSTOMER_EMAIL_REQUIRED = "Customer Email is required";
    public static final String ORDERLINE_CUSTOMER_EMAIL_INVALID = "Customer Email is Invalid";
    public static final String ORDERLINE_CUSTOMER_CONTACT_REQUIRED = "Customer Contact is required";
    public static final String ORDERLINE_CUSTOMER_CONTACT_INVALID = "Customer contact is invalid";
    public static final String ORDERLINE_DATE_REQUIRED = "Orderline Date is required";

    public static final String ORDERLINEITEM_ID_REQUIRED = "OrderLineItem ID must be a positive number";
    public static final String ORDERLINEITEM_STATUS_REQUIRED = "OrderlineItem Status is required";
    public static final String ORDERLINEITEM_PRODUCT_NAME_REQUIRED = "Product name is required";
    public static final String ORDERLINEITEM_PRODUCT_QTY_INVALID = "Product quantity must be greater than 0";
    public static final String ORDERLINEITEM_PRODUCT_BRAND_REQUIRED = "Product brand is required";

}   
