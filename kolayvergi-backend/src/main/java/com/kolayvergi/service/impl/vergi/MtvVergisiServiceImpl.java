package com.kolayvergi.service.impl.vergi;

import com.kolayvergi.dto.mapper.MtvVergisiMapper;
import com.kolayvergi.dto.response.vergi.MtvVergisiResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.vergi.MtvVergisi;
import com.kolayvergi.entity.vergi.Vergi;
import com.kolayvergi.repository.vergi.MtvVergisiRepository;
import com.kolayvergi.service.vergi.MtvVergisiService;
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
public class MtvVergisiServiceImpl implements MtvVergisiService {

    private final MtvVergisiRepository mtvVergisiRepository;
    private final MtvVergisiMapper mtvVergisiMapper;
    private final VergiHesaplamaStrategy mtvVergisiHesaplamaStrategy;
    private final MessageSource messageSource;

    @Override
    @Transactional
    public MtvVergisi createMtvVergisi(Alisveris alisveris, Kullanici kullanici) {
        Vergi vergi = mtvVergisiHesaplamaStrategy.hesapla(alisveris, kullanici);
        return mtvVergisiRepository.save((MtvVergisi) vergi);
    }

    @Override
    @Transactional
    public MtvVergisi updateMtvVergisi(Alisveris alisveris, Kullanici kullanici) {
        List<MtvVergisi> mevcutlar = mtvVergisiRepository.findByAlisverisId(alisveris.getId());
        Vergi yeniMtv = mtvVergisiHesaplamaStrategy.hesapla(alisveris, kullanici);
        if (!mevcutlar.isEmpty()) {
            MtvVergisi mtv = mevcutlar.get(0);
            mtv.setMatrah(yeniMtv.getMatrah());
            mtv.setOran(yeniMtv.getOran());
            mtv.setTutar(yeniMtv.getTutar());
            mtv.setAracBilgisi(((MtvVergisi) yeniMtv).getAracBilgisi());
            return mtvVergisiRepository.save(mtv);
        } else {
            return mtvVergisiRepository.save((MtvVergisi) yeniMtv);
        }
    }

    @Override
    public List<MtvVergisiResponse> getAllByAlisverisId(UUID alisverisId) {
        return mtvVergisiRepository.findByAlisverisId(alisverisId)
                .stream()
                .map(mtvVergisiMapper::mtvVergisiToMtvVergisiResponse)
                .toList();
    }

    @Override
    public MtvVergisiResponse getMtvVergisiById(UUID id) {
        MtvVergisi mtvVergisi = mtvVergisiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("vergi.mtv_vergisi_bulunamadi", new Object[]{id}, LocaleContextHolder.getLocale())));
        return mtvVergisiMapper.mtvVergisiToMtvVergisiResponse(mtvVergisi);
    }
}
