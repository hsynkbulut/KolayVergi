package com.kolayvergi.generator;


import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class VknGenerator {
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

}
