package com.kolayvergi.constant.swagger;

public final class AlisverisSwaggerExample {
    
    private AlisverisSwaggerExample() {
        throw new IllegalStateException("Constant class");
    }

    public static final String OTOMOBIL_ALISVERIS_REQUEST = """
            {
                "urunTuru": "OTOMOBIL",
                "tutar": 1250.00,
                "taksitSayisi": 4,
                "aracBilgisi": {
                    "marka": "BMW",
                    "model": "320i",
                    "ilkTescilYili": "2024",
                    "motorSilindirHacmi": "1601-1800 cm³",
                    "aracTipi": "Otomobil-Kaptıkaçtı-Arazi Taşıtı ve benzerleri",
                    "aracYasi": "1-3 yaş",
                    "aracKapasitesi": "25 kişiye kadar",
                    "aracAgirligi": "1-1.500 kg arası"
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

    public static final String OTOMOBIL_ALISVERIS_RESPONSE = """
            {
                "urunTuru": "OTOMOBIL",
                "tutar": 1250.00,
                "taksitSayisi": 12,
                "aracBilgisi": {
                    "marka": "BMW",
                    "model": "320i",
                    "ilkTescilYili": "2024",
                    "motorSilindirHacmi": "1601-1800 cm³",
                    "aracTipi": "Otomobil-Kaptıkaçtı-Arazi Taşıtı ve benzerleri",
                    "aracYasi": "1-3 yaş",
                    "aracKapasitesi": "25 kişiye kadar",
                    "aracAgirligi": "1-1.500 kg arası"
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