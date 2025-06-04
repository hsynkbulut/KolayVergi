package com.kolayvergi.service.impl.vergi;

import com.kolayvergi.dto.mapper.KdvVergisiMapper;
import com.kolayvergi.dto.response.vergi.KdvVergisiResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.vergi.KdvVergisi;
import com.kolayvergi.entity.vergi.OtvVergisi;
import com.kolayvergi.entity.vergi.Vergi;
import com.kolayvergi.repository.vergi.KdvVergisiRepository;
import com.kolayvergi.service.vergi.KdvVergisiService;
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
public class KdvVergisiServiceImpl implements KdvVergisiService {

    private final KdvVergisiRepository kdvVergisiRepository;
    private final KdvVergisiMapper kdvVergisiMapper;
    private final VergiHesaplamaStrategy kdvVergisiHesaplamaStrategy;
    private final MessageSource messageSource;

    @Override
    @Transactional
    public KdvVergisi createKdvVergisi(Alisveris alisveris, Kullanici kullanici, OtvVergisi otvVergisi) {
        Vergi vergi = kdvVergisiHesaplamaStrategy.hesapla(alisveris, kullanici, otvVergisi);
        return kdvVergisiRepository.save((KdvVergisi) vergi);
    }

    @Override
    public List<KdvVergisiResponse> getAllByAlisverisId(UUID alisverisId) {
        List<KdvVergisiResponse> list = new ArrayList<>();
        for (KdvVergisi kdvVergisi : kdvVergisiRepository.findByAlisverisId(alisverisId)) {
            KdvVergisiResponse kdvVergisiResponse = kdvVergisiMapper.kdvVergisiToKdvVergisiResponse(kdvVergisi);
            list.add(kdvVergisiResponse);
        }
        return list;
    }

    @Override
    public KdvVergisiResponse getKdvVergisiById(UUID id) {
        KdvVergisi kdvVergisi = kdvVergisiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("vergi.kdv_vergisi_bulunamadi", new Object[]{id}, LocaleContextHolder.getLocale())));
        return kdvVergisiMapper.kdvVergisiToKdvVergisiResponse(kdvVergisi);
    }
}
