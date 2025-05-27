package com.kolayvergi.dto.response;

import com.kolayvergi.entity.enums.OdemeDurumu;
import com.kolayvergi.entity.enums.OdemeTuru;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaksitResponse {
    private UUID id;
    private String taksitNo;
    private BigDecimal taksitTutari;
    private LocalDate odemeTarihi;
    private LocalDate sonOdemeTarihi;
    private OdemeDurumu durum;
    private OdemeTuru odemeTuru;
    private UUID odemePlaniId;
} 