package com.kolayvergi.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class KullaniciResponse {

    private String ad;
    private String soyad;
    private String email;
    private String meslek;
    private Integer yas;
    private BigDecimal maas;
}