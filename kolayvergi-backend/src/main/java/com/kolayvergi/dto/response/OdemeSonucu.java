package com.kolayvergi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OdemeSonucu {
    private BigDecimal mevcutTaksitTutari;
    private BigDecimal guncellenmisTutar;
    private BigDecimal faizMiktari;
    private BigDecimal indirimMiktari;
    private BigDecimal cezaMiktari;
    private boolean faizUygulandi;
    private boolean indirimUygulandi;
    private boolean cezaUygulandi;
}