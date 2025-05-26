package com.kolayvergi.dto.response.vergi;

import com.kolayvergi.entity.enums.AracTipi;
import com.kolayvergi.entity.enums.MotorSilindirHacmi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OtvVergisiResponse {
    private UUID alisverisId;
    private BigDecimal fiyat;
    private AracTipi aracTipi;
    private MotorSilindirHacmi motorSilindirHacmi;
}
