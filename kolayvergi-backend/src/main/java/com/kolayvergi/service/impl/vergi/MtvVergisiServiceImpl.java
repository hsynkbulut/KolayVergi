package com.kolayvergi.service.impl.vergi;

import com.kolayvergi.constant.VergiConstants;
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

    @Override
    @Transactional
    public MtvVergisi createMtvVergisi(Alisveris alisveris, Kullanici kullanici) {
        Vergi vergi = mtvVergisiHesaplamaStrategy.hesapla(alisveris, kullanici);
        return mtvVergisiRepository.save((MtvVergisi) vergi);
    }

    @Override
    public List<MtvVergisiResponse> getAllByAlisverisId(UUID alisverisId) {
        List<MtvVergisiResponse> list = new ArrayList<>();
        for (MtvVergisi mtvVergisi : mtvVergisiRepository.findByAlisverisId(alisverisId)) {
            MtvVergisiResponse mtvVergisiResponse = mtvVergisiMapper.mtvVergisiToMtvVergisiResponse(mtvVergisi);
            list.add(mtvVergisiResponse);
        }
        return list;
    }

    @Override
    public MtvVergisiResponse getMtvVergisiById(UUID id) {
        MtvVergisi vergi = mtvVergisiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(VergiConstants.MTV_VERGISI_BULUNAMADI, id)));
        return mtvVergisiMapper.mtvVergisiToMtvVergisiResponse(vergi);
    }
}
