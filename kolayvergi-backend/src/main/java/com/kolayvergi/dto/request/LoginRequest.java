package com.kolayvergi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "validation.email_bos_olamaz")
    @Email(message = "validation.gecerli_email")
    private String email;

    @NotBlank(message = "validation.sifre_bos_olamaz")
    private String password;
} 