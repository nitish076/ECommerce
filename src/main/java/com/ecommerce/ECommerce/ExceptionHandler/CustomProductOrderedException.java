package com.ecommerce.ECommerce.ExceptionHandler;

public class CustomProductOrderedException extends Throwable{

    public CustomProductOrderedException(String document_not_saved) {
        super(document_not_saved);
    }
}
