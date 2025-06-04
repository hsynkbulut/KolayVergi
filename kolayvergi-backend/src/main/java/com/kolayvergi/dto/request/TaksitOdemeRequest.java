package com.kolayvergi.dto.request;

import com.kolayvergi.entity.enums.OdemeTuru;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "validation.taksit_no_bos_olamaz")
    private String taksitNo;

    @NotNull(message = "validation.odeme_tutari_bos_olamaz")
    private BigDecimal odemeTutari;

    @NotNull(message = "validation.odeme_turu_bos_olamaz")
    private OdemeTuru odemeTuru;
}
