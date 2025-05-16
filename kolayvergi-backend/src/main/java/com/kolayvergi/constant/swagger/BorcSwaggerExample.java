package com.kolayvergi.constant.swagger;

public final class BorcSwaggerExample {
    
    private BorcSwaggerExample() {
        throw new IllegalStateException("Constant class");
    }

    // Response örnekleri
    public static final String BORC_RESPONSE = """
            {
                "id": 1,
                "toplamBorc": 300000.00,
                "kalanBorc": 250000.00
            }
            """;
} 