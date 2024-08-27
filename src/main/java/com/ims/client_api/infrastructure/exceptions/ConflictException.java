package com.ims.client_api.infrastructure.exceptions;

public class ConflictException extends RuntimeException{

    public ConflictException(String msg){
        super(msg);
    }

    public ConflictException(String msg, Throwable couse){
        super(msg, couse);
    }
}
