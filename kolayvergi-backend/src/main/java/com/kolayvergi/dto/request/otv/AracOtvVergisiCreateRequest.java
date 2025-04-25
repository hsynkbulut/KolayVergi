package com.kolayvergi.dto.request.otv;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AracOtvVergisiCreateRequest {

    @NotNull
    private Long alisverisId;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal fiyat;

    @NotBlank
    private String aracTipi;

    @NotNull
    @Min(50)
    private Integer motorSilindirHacmi;
}
