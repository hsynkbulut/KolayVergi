package com.kolayvergi.constant.swagger;

public final class TaksitOdemeSwaggerExample {
    
    private TaksitOdemeSwaggerExample() {
        throw new IllegalStateException("Constant class");
    }

    // Request örnekleri
    public static final String TAKSIT_ODEME_REQUEST = """
            {
                "taksitNo": "20250430052200118QB0002",
                "odemeTutari": 5000.00,
                "odemeTuru": "KREDI"
            }
            """;

    // Response örnekleri
    public static final String TAKSIT_ODEME_RESPONSE = """
            {
                "sonOdemeTarihi": "2024-03-15",
                "mevcutTaksitTutari": 30000,
                "guncellenmisTaksitTutari": 20000,
                "faizMiktari": 2000,
                "indirimMiktari": 0,
                "cezaMiktari": 0,
                "odemeTuru": "KREDI"
            }
            """;

    public static final String ODEME_SONUCU_RESPONSE = """
            {
                "mesaj": "Taksit ödemesi başarıyla gerçekleştirildi",
                "odemeDetaylari": {
                    "odemeTutari": 5000.00,
                    "odemeTarihi": "2024-03-15",
                    "kalanTaksitSayisi": 5,
                    "toplamKalanBorc": 25000.00
                }
            }
            """;
} 