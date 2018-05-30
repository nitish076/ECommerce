package com.ecommerce.ECommerce.ExceptionHandler;

public class CustomProductRatedException extends Throwable{

    public CustomProductRatedException(String document_not_saved) {
        super(document_not_saved);
    }
}
