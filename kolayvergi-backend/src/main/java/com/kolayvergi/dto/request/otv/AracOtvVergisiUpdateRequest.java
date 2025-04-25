package com.kolayvergi.dto.request.otv;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AracOtvVergisiUpdateRequest {

    @DecimalMin("0.01")
    private BigDecimal fiyat;

    @Size(min = 1)
    private String aracTipi;

    @Min(50)
    private Integer motorSilindirHacmi;
}
