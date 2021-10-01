package com.bmd.connecty.connectstart.handlers;

import com.bmd.connecty.connectstart.exceptions.ServerException;
import com.bmd.connecty.connectstart.exceptions.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.bmd.connecty.connectstart.exceptions.MatrixException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MatrixException.class)
    public ResponseEntity<String> handleMatrixException(MatrixException exception ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> handleUserException(UserException exception ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<String> handleServerException(ServerException exception ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

}
