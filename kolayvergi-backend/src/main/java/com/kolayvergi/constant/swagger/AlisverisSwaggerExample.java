package com.kolayvergi.constant.swagger;

public final class AlisverisSwaggerExample {
    
    private AlisverisSwaggerExample() {
        throw new IllegalStateException("Constant class");
    }

    // Request örnekleri
    public static final String OTOMOBIL_ALISVERIS_REQUEST = """
            {
                "urunTuru": "OTOMOBIL",
                "tutar": 500000.00,
                "taksitSayisi": 12,
                "aracBilgisi": {
                    "marka": "BMW",
                    "model": "320i",
                    "ilkTescilYili": 2023,
                    "motorSilindirHacmi": 2000,
                    "aracTipi": "SEDAN",
                    "aracYasi": 5
                }
            }
            """;

    public static final String DIGER_URUN_ALISVERIS_REQUEST = """
            {
                "urunTuru": "GIDA",
                "tutar": 1500.00,
                "taksitSayisi": 1
            }
            """;

    // Response örnekleri
    public static final String OTOMOBIL_ALISVERIS_RESPONSE = """
            {
                "urunTuru": "OTOMOBIL",
                "tutar": 500000.00,
                "taksitSayisi": 12,
                "aracBilgisi": {
                    "marka": "BMW",
                    "model": "320i",
                    "ilkTescilYili": 2023,
                    "motorSilindirHacmi": 2000,
                    "aracTipi": "SEDAN",
                    "aracYasi": 5
                }
            }
            """;

    public static final String DIGER_URUN_ALISVERIS_RESPONSE = """
            {
                "urunTuru": "GIDA",
                "tutar": 1500.00,
                "taksitSayisi": 1,
            }
            """;
} 