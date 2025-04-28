package com.kolayvergi.service.impl;

import com.kolayvergi.dto.mapper.AlisverisMapper;
import com.kolayvergi.dto.request.AlisverisCreateRequest;
import com.kolayvergi.dto.request.AlisverisUpdateRequest;
import com.kolayvergi.dto.response.AlisverisResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.AracBilgisi;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.enums.UrunTuru;
import com.kolayvergi.entity.enums.VergiTuru;
import com.kolayvergi.factory.VergiTuruBelirleyici;
import com.kolayvergi.repository.AlisverisRepository;
import com.kolayvergi.service.AlisverisService;
import com.kolayvergi.service.AracBilgisiService;
import com.kolayvergi.service.KullaniciService;
import com.kolayvergi.service.vergi.AracOtvVergisiService;
import com.kolayvergi.service.vergi.KdvVergisiService;
import com.kolayvergi.service.vergi.MtvVergisiService;
import com.kolayvergi.strategy.VergiHesaplayiciFactory;
import com.kolayvergi.strategy.VergiHesaplayiciStrategy;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlisverisServiceImpl implements AlisverisService {

    private final AlisverisRepository alisverisRepository;
    private final KullaniciService kullaniciService;
    private final AlisverisMapper alisverisMapper;
    private final AracBilgisiService aracBilgisiService;
    private final VergiTuruBelirleyici vergiTuruBelirleyici;
    private final VergiHesaplayiciFactory vergiHesaplayiciFactory;
    private final KdvVergisiService kdvVergisiService;
    private final MtvVergisiService mtvVergisiService;
    private final AracOtvVergisiService aracOtvVergisiService;

    @Transactional(readOnly = false)
    @Override
    public AlisverisResponse createAlisveris(AlisverisCreateRequest request) {
        Kullanici kullanici = kullaniciService.getKullanici(request.getKullaniciId());
        Alisveris alisveris = alisverisMapper.aliverisCreateRequestToAlisveris(request);
        alisveris.setKullanici(kullanici);

        if (request.getUrunTuru() == UrunTuru.OTOMOBIL) {
            if (request.getAracBilgisi() == null) {
                throw new IllegalArgumentException("Otomobil kategorisinde araç bilgisi zorunludur.");
            }
            AracBilgisi aracBilgisi = aracBilgisiService.createAracBilgisiForAlisveris(alisveris, request.getAracBilgisi());
            alisveris.setAracBilgisi(aracBilgisi);
        }

        // Vergi türlerini belirle
        List<VergiTuru> vergiTurleri = vergiTuruBelirleyici.getVergiTurleri(alisveris.getUrunTuru());

        // Vergileri hesapla ve kaydet
        for (VergiTuru vergiTuru : vergiTurleri) {
            VergiHesaplayiciStrategy hesaplayici = vergiHesaplayiciFactory.getStrategy(vergiTuru);
            BigDecimal vergiTutari = hesaplayici.hesapla(alisveris.getTutar(), kullanici);

            switch (vergiTuru) {
                case KDV:
                    kdvVergisiService.createKdvVergisi(alisveris, kullanici);
                    break;
                case MTV:
                    mtvVergisiService.createMtvVergisi(alisveris, kullanici);
                    break;
                case OTV:
                    aracOtvVergisiService.createAracOtvVergisi(alisveris, kullanici);
                    break;
                default:
                    throw new IllegalArgumentException("Bilinmeyen vergi türü: " + vergiTuru);
            }
        }

        Alisveris dbAlisveris = alisverisRepository.save(alisveris);
        return alisverisMapper.alisverisToAlisverisResponse(dbAlisveris);
    }

    @Override
    public AlisverisResponse getAlisveris(Long id){
        Alisveris alisveris = getAlisverisById(id);
        return alisverisMapper.alisverisToAlisverisResponse(alisveris);
    }

    @Override
    @Transactional(readOnly = false)
    public AlisverisResponse updateAlisveris(Long id, AlisverisUpdateRequest updateAlisveris) {
        Alisveris alisveris = getAlisverisById(id);

        Optional.ofNullable(updateAlisveris.getUrunTuru()).ifPresent(alisveris::setUrunTuru);
        Optional.ofNullable(updateAlisveris.getTutar()).ifPresent(alisveris::setTutar);
        Optional.ofNullable(updateAlisveris.getTaksitSayisi()).ifPresent(alisveris::setTaksitSayisi);

        if (updateAlisveris.getKullaniciId() != null) {
            Kullanici kullanici = kullaniciService.getKullanici(updateAlisveris.getKullaniciId());
            alisveris.setKullanici(kullanici);
        }

        Alisveris updatedAlisveris = alisverisRepository.save(alisveris);
        return alisverisMapper.alisverisToAlisverisResponse(updatedAlisveris);
    }


    @Transactional(readOnly = false)
    @Override
    public void deleteAlisveris(Long id) {
        if (!alisverisRepository.existsById(id)) {
            throw new EntityNotFoundException("Silinecek alışveriş bulunamadı: " + id);
        }
        alisverisRepository.deleteById(id);
    }

    public Alisveris getAlisverisById(Long id){
        return alisverisRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alışveriş bulunamadı: " + id));
    }

}
