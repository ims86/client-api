package com.ims.client_api.infrastructure.exceptions.handler;

import com.ims.client_api.infrastructure.exceptions.BusinessException;
import com.ims.client_api.infrastructure.exceptions.ConflictException;
import com.ims.client_api.infrastructure.exceptions.UnprocessableEntityException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErrorResponse> response(String message, HttpServletRequest request, HttpStatus status, List<String> errors){
        return ResponseEntity
                .status(status)
                .body(new ErrorResponse(message, status.value(), request.getRequestURI(), errors));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handlerBusinessException(BusinessException e, HttpServletRequest request){
        return response(e.getMessage(), request, HttpStatus.BAD_REQUEST, new ArrayList<>());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        BindingResult bindingResult = e.getBindingResult();
        List<String> messages = bindingResult.getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());

        return response("Erro ao validar dados", request, HttpStatus.BAD_REQUEST, messages);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<ErrorResponse> handlerUnprocessableEntityException(UnprocessableEntityException e, HttpServletRequest request){
        return response(e.getMessage(), request, HttpStatus.UNPROCESSABLE_ENTITY, new ArrayList<>());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handlerConflictException(ConflictException e, HttpServletRequest request){
        return response(e.getMessage(), request, HttpStatus.CONFLICT, new ArrayList<>());
    }
}
