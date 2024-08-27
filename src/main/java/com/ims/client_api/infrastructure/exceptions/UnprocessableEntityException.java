package com.ims.client_api.infrastructure.exceptions;

public class UnprocessableEntityException extends RuntimeException{

    public UnprocessableEntityException(String msg){
        super(msg);
    }

    public UnprocessableEntityException(String msg, Throwable couse){
        super(msg, couse);
    }
}
