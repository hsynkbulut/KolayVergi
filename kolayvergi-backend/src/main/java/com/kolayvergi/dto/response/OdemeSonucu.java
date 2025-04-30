package com.kolayvergi.dto.response;

import lombok.*;

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