package com.ims.client_api.infrastructure.exceptions;

public class BusinessException extends RuntimeException{

    public BusinessException(String msg){
        super(msg);
    }

    public BusinessException(String msg, Throwable couse){
        super(msg, couse);
    }
}
