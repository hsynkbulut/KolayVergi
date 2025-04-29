package com.kolayvergi.dto.request;

import com.kolayvergi.entity.enums.OdemeTuru;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaksitOdemeRequest {
    private String taksitNo;
    private BigDecimal odemeTutari;
    private OdemeTuru odemeTuru;
}
