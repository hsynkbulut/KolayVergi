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

    public static final String OTOMOBIL_ALISVERIS_UPDATE_REQUEST = """
            {
                "urunTuru": "OTOMOBIL",
                "tutar": 1250.00,
                "taksitSayisi": 4,
                "aracBilgisi": {
                    "id": "b3e1c2d4-1234-5678-9abc-def012345678",
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

    public static final String ALISVERIS_NOT_FOUND_RESPONSE = """
        {
          "id": "3c54feb8-b6f3-48c9-86fc-c98c0039fc17",
          "errorTime": "2025-06-05T13:11:12.801+00:00",
          "status": 404,
          "userMessage": "İstenen kayıt bulunamadı.",
          "developerMessage": "Alışveriş bulunamadı: cc0abf20-1831-4306-abde-c5cffc48b319",
          "path": "/api/v1/alisverisler/cc0abf20-1831-4306-abde-c5cffc48b319",
          "errors": null
        }
        """;
    
    public static final String ALISVERIS_DELETE_NOT_FOUND_RESPONSE = """
        {
          "id": "3c54feb8-b6f3-48c9-86fc-c98c0039fc17",
          "errorTime": "2025-06-05T13:11:12.801+00:00",
          "status": 404,
          "userMessage": "İstenen kayıt bulunamadı.",
          "developerMessage": "Silinecek alışveriş bulunamadı: cc0abf20-1831-4306-abde-c5cffc48b319",
          "path": "/api/v1/alisverisler/cc0abf20-1831-4306-abde-c5cffc48b319",
          "errors": null
        }
        """;
} 