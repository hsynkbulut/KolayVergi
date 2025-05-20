package com.kolayvergi.config;

import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.enums.Role;
import com.kolayvergi.generator.VknGenerator;
import com.kolayvergi.repository.KullaniciRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminUserInitializer implements CommandLineRunner {

    private final KullaniciRepository kullaniciRepository;
    private final PasswordEncoder passwordEncoder;
    private final VknGenerator vknGenerator;

    @Override
    public void run(String... args) {
        if (!kullaniciRepository.existsByEmail("admin@kolayvergi.com")) {
            String rawPassword = "Admin123!";
            Kullanici admin = new Kullanici();
            admin.setEmail("admin@kolayvergi.com");
            admin.setSifre(passwordEncoder.encode(rawPassword));
            admin.setAd("Admin");
            admin.setSoyad("User");
            admin.setTckn("11111111110");
            admin.setVkn(vknGenerator.generateUniqueVkn());
            admin.setYas(30);
            admin.setRol(Role.ROLE_ADMIN);
            admin.setCinsiyet(com.kolayvergi.entity.enums.Cinsiyet.ERKEK);
            admin.setMeslek(com.kolayvergi.entity.enums.Meslek.DIGER);
            admin.setMaas(java.math.BigDecimal.valueOf(10000));
            
            kullaniciRepository.save(admin);
        }
    }

} 