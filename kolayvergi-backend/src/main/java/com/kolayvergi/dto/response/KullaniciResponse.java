package com.kolayvergi.dto.response;


import com.kolayvergi.entity.enums.Cinsiyet;
import com.kolayvergi.entity.enums.Meslek;
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
    private Cinsiyet cinsiyet;
    private Meslek meslek;
    private Integer yas;
    private BigDecimal maas;
}