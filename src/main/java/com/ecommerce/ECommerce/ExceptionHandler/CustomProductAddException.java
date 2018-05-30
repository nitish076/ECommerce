package com.ecommerce.ECommerce.ExceptionHandler;

public class CustomProductAddException extends Throwable {

    public CustomProductAddException(String document_not_saved) {
        super(document_not_saved);
    }
}
