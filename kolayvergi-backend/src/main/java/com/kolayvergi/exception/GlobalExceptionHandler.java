package com.kolayvergi.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.security.access.AccessDeniedException;

import java.util.*;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError<Map<String, List<String>>>> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, List<String>> errorMap = new HashMap<>();

        for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
            String fieldName;
            if (objectError instanceof FieldError fieldError) {
                fieldName = fieldError.getField();
            } else {
                fieldName = objectError.getObjectName();
            }
            String defaultMessage = objectError.getDefaultMessage();
            String localizedMessage = defaultMessage != null
                ? messageSource.getMessage(defaultMessage, null, defaultMessage, LocaleContextHolder.getLocale())
                : "";
            errorMap.computeIfAbsent(fieldName, k -> new ArrayList<>()).add(localizedMessage);
        }

        ApiError<Map<String, List<String>>> apiError = createApiError(
                errorMap,
                HttpStatus.BAD_REQUEST,
                messageSource.getMessage("genel.gonderilen_verilerde_hata", null, LocaleContextHolder.getLocale()),
                messageSource.getMessage("genel.validation_hatasi", null, LocaleContextHolder.getLocale()),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError<String>> handleEntityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
        ApiError<String> apiError = createApiError(
                null,
                HttpStatus.NOT_FOUND,
                messageSource.getMessage("genel.kayit_bulunamadi", null, LocaleContextHolder.getLocale()),
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
                messageSource.getMessage("genel.url_parametre_eksik", null, LocaleContextHolder.getLocale()),
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
                messageSource.getMessage("genel.gecersiz_veri", null, LocaleContextHolder.getLocale()),
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
                messageSource.getMessage("genel.sistem_hatasi", null, LocaleContextHolder.getLocale()),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError<String>> handleException(Exception ex, HttpServletRequest request) {
        ApiError<String> apiError = createApiError(
                null,
                HttpStatus.INTERNAL_SERVER_ERROR,
                messageSource.getMessage("genel.beklenmeyen_hata", null, LocaleContextHolder.getLocale()),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError<String>> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        ApiError<String> apiError = createApiError(
                null,
                HttpStatus.FORBIDDEN,
                messageSource.getMessage("genel.yetkisiz_erisim", null, "Bu kaynağa erişim yetkiniz yok.", LocaleContextHolder.getLocale()),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiError);
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