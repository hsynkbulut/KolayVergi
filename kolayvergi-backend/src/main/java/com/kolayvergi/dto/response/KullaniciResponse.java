package com.kolayvergi.dto.response;


import com.kolayvergi.entity.enums.Cinsiyet;
import com.kolayvergi.entity.enums.Meslek;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KullaniciResponse {

    private String ad;
    private String soyad;
    private String email;
    private Cinsiyet cinsiyet;
    private Meslek meslek;
    private Integer yas;
    private BigDecimal maas;
}