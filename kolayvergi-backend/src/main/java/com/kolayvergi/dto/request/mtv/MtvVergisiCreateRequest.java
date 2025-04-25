package com.kolayvergi.dto.request.mtv;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MtvVergisiCreateRequest {

    @NotNull
    private Long alisverisId;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal fiyat;

    @NotBlank
    private String aracTipi;

    @NotNull
    @Min(0)
    private Integer aracYasi;

    @NotNull
    @Min(50)
    private Integer motorSilindirHacmi;

    @NotNull
    @Min(1950)
    private Integer ilkTescilYili;
}
