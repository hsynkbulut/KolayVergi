package com.kolayvergi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError<T> {
    private String id;
    private Date errorTime;
    private int status;
    private String userMessage;
    private String developerMessage;
    private String path;
    private T errors;
}
