package com.kolayvergi.service.impl.vergi;

import com.kolayvergi.dto.mapper.OtvVergisiMapper;
import com.kolayvergi.dto.response.vergi.OtvVergisiResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.vergi.OtvVergisi;
import com.kolayvergi.entity.vergi.Vergi;
import com.kolayvergi.repository.vergi.OtvVergisiRepository;
import com.kolayvergi.service.vergi.OtvVergisiService;
import com.kolayvergi.strategy.VergiHesaplamaStrategy;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class OtvVergisiServiceImpl implements OtvVergisiService {

    private final OtvVergisiMapper otvVergisiMapper;
    private final OtvVergisiRepository otvVergisiRepository;
    private final VergiHesaplamaStrategy otvVergisiHesaplamaStrategy;
    private final MessageSource messageSource;

    @Override
    @Transactional
    public OtvVergisi createOtvVergisi(Alisveris alisveris, Kullanici kullanici) {
        Vergi vergi = otvVergisiHesaplamaStrategy.hesapla(alisveris, kullanici);
        return otvVergisiRepository.save((OtvVergisi) vergi);
    }

    @Override
    public List<OtvVergisiResponse> getAllByAlisverisId(UUID alisverisId) {
        return otvVergisiRepository.findByAlisverisId(alisverisId)
                .stream()
                .map(otvVergisiMapper::otvVergisiToOtvVergisiResponse)
                .toList();
    }

    @Override
    public OtvVergisiResponse getOtvVergisiById(UUID id) {
        OtvVergisi otvVergisi = otvVergisiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("vergi.otv_vergisi_bulunamadi", new Object[]{id}, LocaleContextHolder.getLocale())));
        return otvVergisiMapper.otvVergisiToOtvVergisiResponse(otvVergisi);
    }

    @Override
    @Transactional
    public OtvVergisi updateOtvVergisi(Alisveris alisveris, Kullanici kullanici) {
        List<OtvVergisi> mevcutlar = otvVergisiRepository.findByAlisverisId(alisveris.getId());
        Vergi yeniOtv = otvVergisiHesaplamaStrategy.hesapla(alisveris, kullanici);
        if (!mevcutlar.isEmpty()) {
            OtvVergisi otv = mevcutlar.get(0);
            otv.setMatrah(yeniOtv.getMatrah());
            otv.setOran(yeniOtv.getOran());
            otv.setTutar(yeniOtv.getTutar());
            otv.setUrunTuru(((OtvVergisi) yeniOtv).getUrunTuru());
            otv.setAracBilgisi(((OtvVergisi) yeniOtv).getAracBilgisi());
            otv.setLuksUrunKatSayisi(((OtvVergisi) yeniOtv).getLuksUrunKatSayisi());
            return otvVergisiRepository.save(otv);
        } else {
            return otvVergisiRepository.save((OtvVergisi) yeniOtv);
        }
    }
}
