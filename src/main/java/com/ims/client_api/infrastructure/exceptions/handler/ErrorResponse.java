package com.ims.client_api.infrastructure.exceptions.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private int status;
    private String path;
    private List<String> errors;
}
