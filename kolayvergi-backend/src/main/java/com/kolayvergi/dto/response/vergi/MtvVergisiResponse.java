package com.kolayvergi.dto.response.vergi;

import com.kolayvergi.entity.enums.AracTipi;
import com.kolayvergi.entity.enums.AracYasi;
import com.kolayvergi.entity.enums.IlkTescilYili;
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
public class MtvVergisiResponse {
    private UUID alisverisId;
    private BigDecimal fiyat;
    private AracTipi aracTipi;
    private AracYasi aracYasi;
    private MotorSilindirHacmi motorSilindirHacmi;
    private IlkTescilYili ilkTescilYili;
}
