package com.kolayvergi.dto.request.mtv;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MtvVergisiUpdateRequest {

    @DecimalMin("0.01")
    private BigDecimal fiyat;

    @Size(min = 1)
    private String aracTipi;

    @Min(0)
    private Integer aracYasi;

    @Min(50)
    private Integer motorSilindirHacmi;

    @Min(1950)
    private Integer ilkTescilYili;
}
