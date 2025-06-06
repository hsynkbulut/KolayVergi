package com.kolayvergi.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record BorcDurumu(UUID kullaniciId, BigDecimal toplamBorc, BigDecimal kalanBorc) {} 