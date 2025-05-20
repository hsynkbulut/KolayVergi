package com.kolayvergi.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError<Map<String, List<String>>>> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, List<String>> errorMap = new HashMap<>();

        for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
            String fieldName = (objectError instanceof FieldError) ? ((FieldError) objectError).getField() : objectError.getObjectName();
            errorMap.computeIfAbsent(fieldName, k -> new ArrayList<>()).add(objectError.getDefaultMessage());
        }

        ApiError<Map<String, List<String>>> apiError = createApiError(
                errorMap,
                HttpStatus.BAD_REQUEST,
                "Gönderdiğiniz verilerde eksiklik veya hata var.",
                "Validation hatası oluştu.",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError<String>> handleEntityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
        ApiError<String> apiError = createApiError(
                null,
                HttpStatus.NOT_FOUND,
                "İstenen kayıt bulunamadı.",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<ApiError<String>> handleMissingPathVariableException(MissingPathVariableException ex, HttpServletRequest request) {
        ApiError<String> apiError = createApiError(
                null,
                HttpStatus.BAD_REQUEST,
                "URL parametrelerinden biri eksik.",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError<String>> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        ApiError<String> apiError = createApiError(
                null,
                HttpStatus.BAD_REQUEST,
                "Geçersiz veri gönderildi.",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError<String>> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
        ApiError<String> apiError = createApiError(
                null,
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Sistemde bir hata oluştu.",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError<String>> handleGenericException(Exception ex, HttpServletRequest request) {
        ApiError<String> apiError = createApiError(
                null,
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Beklenmeyen bir hata oluştu. Lütfen daha sonra tekrar deneyin.",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    private <T> ApiError<T> createApiError(T errors, HttpStatus status, String userMessage, String developerMessage, String path) {
        ApiError<T> apiError = new ApiError<>();
        apiError.setId(UUID.randomUUID().toString());
        apiError.setErrorTime(new Date());
        apiError.setStatus(status.value());
        apiError.setUserMessage(userMessage);
        apiError.setDeveloperMessage(developerMessage);
        apiError.setPath(path);
        apiError.setErrors(errors);
        return apiError;
    }
}
