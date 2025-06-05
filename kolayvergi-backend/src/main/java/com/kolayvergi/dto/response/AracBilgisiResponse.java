package com.kolayvergi.dto.response;

import com.kolayvergi.entity.enums.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AracBilgisiResponse {
    private UUID id;
    private String marka;
    private String model;
    private IlkTescilYili ilkTescilYili;
    private MotorSilindirHacmi motorSilindirHacmi;
    private AracTipi aracTipi;
    private AracYasi aracYasi;
    private AracKapasitesi aracKapasitesi;
    private AracAgirligi aracAgirligi;
}
