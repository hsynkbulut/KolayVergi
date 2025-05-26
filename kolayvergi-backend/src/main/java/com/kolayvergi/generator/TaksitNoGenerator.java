package com.kolayvergi.generator;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class TaksitNoGenerator {
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

    /*
    * Yaptığım değişiklikler:
    formatUserId metodunu UUID için uygun hale getirdim
    UUID'nin hashCode() metodunu kullanarak 1-999 arasında bir sayı üretiyoruz
    Math.abs() ile negatif değerleri pozitife çeviriyoruz
    % 999 ile 0-998 arasında bir sayı elde ediyoruz
    + 1 ile 1-999 arasında bir sayı elde ediyoruz
    Son olarak bu sayıyı 3 haneli string formatına çeviriyoruz
    Bu değişiklik ile:
    UUID'den her zaman 1-999 arasında bir sayı üretilecek
    Aynı UUID için her zaman aynı sayı üretilecek
    Taksit numarası formatı korunacak
    *
    */

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