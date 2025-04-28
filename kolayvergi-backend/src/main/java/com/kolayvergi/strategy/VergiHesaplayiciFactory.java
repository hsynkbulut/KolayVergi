package com.kolayvergi.strategy;

import com.kolayvergi.entity.enums.VergiTuru;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class VergiHesaplayiciFactory {

    private final List<VergiHesaplayiciStrategy> stratejiler;
    private final Map<VergiTuru, VergiHesaplayiciStrategy> stratejiMap = new EnumMap<>(VergiTuru.class);

    @PostConstruct
    public void init() {
        for (VergiTuru vergiTuru : VergiTuru.values()) {
            stratejiler.stream()
                    .filter(s -> s.getClass().getAnnotation(Component.class).value().equals(vergiTuru.name()))
                    .findFirst()
                    .ifPresent(s -> stratejiMap.put(vergiTuru, s));
        }
    }

    public VergiHesaplayiciStrategy getStrategy(VergiTuru vergiTuru) {
        return stratejiMap.get(vergiTuru);
    }
}
