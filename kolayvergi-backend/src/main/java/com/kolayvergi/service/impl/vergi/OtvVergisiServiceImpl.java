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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class OtvVergisiServiceImpl implements OtvVergisiService {

    private final OtvVergisiMapper otvVergisiMapper;
    private final OtvVergisiRepository otvVergisiRepository;
    private final VergiHesaplamaStrategy otvVergisiHesaplamaStrategy;

    @Override
    @Transactional
    public OtvVergisi createOtvVergisi(Alisveris alisveris, Kullanici kullanici) {
        Vergi vergi = otvVergisiHesaplamaStrategy.hesapla(alisveris, kullanici);
        return otvVergisiRepository.save((OtvVergisi) vergi);
    }

    @Override
    public List<OtvVergisiResponse> getAllByAlisverisId(Long alisverisId) {
        List<OtvVergisiResponse> list = new ArrayList<>();
        for (OtvVergisi otvVergisi : otvVergisiRepository.findByAlisverisId(alisverisId)) {
            OtvVergisiResponse otvVergisiResponse = otvVergisiMapper.otvVergisiToOtvVergisiResponse(otvVergisi);
            list.add(otvVergisiResponse);
        }
        return list;
    }

    @Override
    public OtvVergisiResponse getOtvVergisiById(Long id) {
        OtvVergisi vergi = otvVergisiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ÖTV Vergisi bulunamadı: " + id));
        return otvVergisiMapper.otvVergisiToOtvVergisiResponse(vergi);
    }
}
