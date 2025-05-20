package com.securitascash.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.securitascash.model.ResponseModel;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Exceção personalizada (ex: recurso não encontrado)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseModel<Object>> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        
        ResponseModel<Object> response = new ResponseModel<>(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            null
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Qualquer outra exceção não tratada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseModel<Object>> handleGlobalException(Exception ex, WebRequest request) {
        
        String path = request.getDescription(false);

        ResponseModel<Object> response = new ResponseModel<>(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Erro interno: " + ex.getMessage() + " | " + path,
            null
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BussinessException.class)
    public ResponseEntity<ResponseModel<Object>> handleBussinessException(Exception ex, WebRequest request) {
        
        ResponseModel<Object> response = new ResponseModel<>(
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage(),
            null
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

