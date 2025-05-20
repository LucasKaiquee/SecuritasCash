package com.securitascash.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseModel<T> {
    private int status;
    private String message;
    private T data;
}
