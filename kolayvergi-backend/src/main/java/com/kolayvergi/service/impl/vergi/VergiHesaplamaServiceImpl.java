package com.kolayvergi.service.impl.vergi;

import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.enums.VergiTuru;
import com.kolayvergi.entity.vergi.KdvVergisi;
import com.kolayvergi.entity.vergi.MtvVergisi;
import com.kolayvergi.entity.vergi.OtvVergisi;
import com.kolayvergi.service.factory.VergiTuruBelirleyici;
import com.kolayvergi.service.vergi.KdvVergisiService;
import com.kolayvergi.service.vergi.MtvVergisiService;
import com.kolayvergi.service.vergi.OtvVergisiService;
import com.kolayvergi.service.vergi.VergiHesaplamaService;
import com.kolayvergi.service.vergi.VergiHesaplamaSonuc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VergiHesaplamaServiceImpl implements VergiHesaplamaService {

    private final VergiTuruBelirleyici vergiTuruBelirleyici;
    private final KdvVergisiService kdvVergisiService;
    private final OtvVergisiService otvVergisiService;
    private final MtvVergisiService mtvVergisiService;

    @Override
    public VergiHesaplamaSonuc hesaplaVergiler(Alisveris alisveris, Kullanici kullanici) {
        List<VergiTuru> vergiTurleri = vergiTuruBelirleyici.getVergiTurleri(alisveris.getUrunTuru());
        
        BigDecimal toplamVergiTutari = BigDecimal.ZERO;
        OtvVergisi otvVergisi = null;
        KdvVergisi kdvVergisi = null;
        MtvVergisi mtvVergisi = null;

        if (vergiTurleri.contains(VergiTuru.OTV)) {
            otvVergisi = otvVergisiService.createOtvVergisi(alisveris, kullanici);
            toplamVergiTutari = toplamVergiTutari.add(otvVergisi.getTutar());
        }
        
        if (vergiTurleri.contains(VergiTuru.KDV)) {
            kdvVergisi = kdvVergisiService.createKdvVergisi(alisveris, kullanici, otvVergisi);
            toplamVergiTutari = toplamVergiTutari.add(kdvVergisi.getTutar());
        }
        
        if (vergiTurleri.contains(VergiTuru.MTV)) {
            mtvVergisi = mtvVergisiService.createMtvVergisi(alisveris, kullanici);
            toplamVergiTutari = toplamVergiTutari.add(mtvVergisi.getTutar());
        }

        return VergiHesaplamaSonuc.builder()
                .toplamVergiTutari(toplamVergiTutari)
                .otvVergisi(otvVergisi)
                .kdvVergisi(kdvVergisi)
                .mtvVergisi(mtvVergisi)
                .build();
    }
} 