package com.kolayvergi.generator;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TaksitNoGenerator {
    public String generateTaksitNo(Long kullaniciId, int index) {
        String timestamp = generateTimeStamp();
        String formattedKullaniciId = formatUserId(kullaniciId);
        String formattedIndex = String.format("%04d", index);
        String checkIndex = generateCheckIndex(timestamp, formattedKullaniciId, formattedIndex);

        return timestamp + formattedKullaniciId + checkIndex + formattedIndex;
    }

    private static String generateTimeStamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
    }

    private String formatUserId(Long userId) {
        if (userId < 1 || userId > 999) {
            throw new IllegalArgumentException("UserId 1-999 arasında olmalı.");
        }
        return String.format("%03d", userId);
    }

    private String generateCheckIndex(String timestamp, String userId, String sequence) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        try {
            String input = timestamp + userId + sequence;

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));

            long seed = bytesToLong(hash);

            SecureRandom secureRandom = new SecureRandom();
            secureRandom.setSeed(seed);

            StringBuilder checkIndex = new StringBuilder(4);
            for (int i = 0; i < 4; i++) {
                int randomIndex = secureRandom.nextInt(chars.length());
                checkIndex.append(chars.charAt(randomIndex));
            }
            return checkIndex.toString();
        } catch (Exception e) {
            throw new RuntimeException("CheckIndex üretimi sırasında hata oluştu", e);
        }
    }

    private long bytesToLong(byte[] bytes) {
        long value = 0;
        for (int i = 0; i < 8 && i < bytes.length; i++) {
            value = (value << 8) + (bytes[i] & 0xff);
        }
        return value;
    }
}