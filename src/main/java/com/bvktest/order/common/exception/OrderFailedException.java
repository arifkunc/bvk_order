package com.bvktest.order.common.exception;

public class OrderFailedException extends RuntimeException{
    public OrderFailedException(String message) {
        super(message);
    }
}
