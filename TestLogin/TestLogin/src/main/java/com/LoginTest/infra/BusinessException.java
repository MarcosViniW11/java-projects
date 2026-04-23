package com.LoginTest.infra;

public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}
