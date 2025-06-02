package com.kolayvergi.dto.request;

import com.kolayvergi.constant.ValidationConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = ValidationConstants.EMAIL_BOS_OLAMAZ)
    @Email(message = ValidationConstants.GECERLI_EMAIL)
    private String email;

    @NotBlank(message = ValidationConstants.SIFRE_BOS_OLAMAZ)
    private String password;
} 