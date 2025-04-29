package com.kolayvergi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError<T> {
    private String id;                 // Hata kimliği (UUID)
    private Date errorTime;             // Hatanın oluştuğu zaman
    private int status;                 // HTTP durum kodu (örn: 400, 404, 500)
    private String userMessage;         // Kullanıcıya gösterilecek basit mesaj
    private String developerMessage;    // Geliştiriciye yönelik teknik detay
    private String path;                // Hatanın oluştuğu API endpointi
    private T errors;                   // Hata detayları (Field hataları, açıklamalar vs.)
}
