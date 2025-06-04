package com.kolayvergi.generator;

import org.springframework.stereotype.Component;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class TaksitNoGenerator {
    private final MessageSource messageSource;

    public String generateTaksitNo(UUID kullaniciId, int index) {
        String timestamp = generateTimeStamp();
        String formattedKullaniciId = formatUserId(kullaniciId);
        String formattedIndex = String.format("%04d", index);
        String checkIndex = generateCheckIndex(timestamp, formattedKullaniciId, formattedIndex);

        return timestamp + formattedKullaniciId + checkIndex + formattedIndex;
    }

    private static String generateTimeStamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
    }

    private String formatUserId(UUID userId) {
        int hashValue = Math.abs(userId.hashCode() % 999) + 1;
        return String.format("%03d", hashValue);
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
            throw new RuntimeException(
                messageSource.getMessage("taksitno.checkindex_hatasi", null, LocaleContextHolder.getLocale()), e
            );
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