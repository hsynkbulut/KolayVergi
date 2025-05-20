package com.kolayvergi.generator;


import com.kolayvergi.repository.KullaniciRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class VknGenerator {

    private final KullaniciRepository kullaniciRepository;
    private final Random random = new Random();

    public String generate() {
        // İlk rakam 1-9 arası olmalı
        StringBuilder vkn = new StringBuilder();
        vkn.append(random.nextInt(9) + 1); // 1-9

        // Kalan 9 rakam 0-9 arası
        for (int i = 0; i < 9; i++) {
            vkn.append(random.nextInt(10)); // 0-9
        }

        return vkn.toString();
    }

    public String generateUniqueVkn() {
        String vkn = generate();
        int denemeSayisi = 1;

        while (kullaniciRepository.existsByVkn(vkn)) {
            if (denemeSayisi >= 50) {
                throw new RuntimeException("Benzersiz VKN üretilemedi.");
            }

            vkn = generate();
            denemeSayisi++;
        }
        return vkn;
    }

}
