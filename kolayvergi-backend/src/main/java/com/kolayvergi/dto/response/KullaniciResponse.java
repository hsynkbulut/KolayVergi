package com.kolayvergi.dto.response;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KullaniciResponse {

    private String ad;
    private String soyad;
    private String email;
    private String meslek;
    private Integer yas;
    private BigDecimal maas;
}