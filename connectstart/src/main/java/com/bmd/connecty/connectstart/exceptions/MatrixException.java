package com.bmd.connecty.connectstart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MatrixException extends RuntimeException{
    public MatrixException(String message) {
        super(message);
    }
}
