package com.kolayvergi.service.impl.vergi;


import com.kolayvergi.dto.mapper.KdvVergisiMapper;
import com.kolayvergi.dto.response.vergi.KdvVergisiResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.enums.VergiTuru;
import com.kolayvergi.entity.vergi.KdvVergisi;
import com.kolayvergi.repository.KdvVergisiRepository;
import com.kolayvergi.service.vergi.KdvVergisiService;
import com.kolayvergi.strategy.VergiHesaplayiciFactory;
import com.kolayvergi.strategy.VergiHesaplayiciStrategy;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KdvVergisiServiceImpl implements KdvVergisiService {

    private final KdvVergisiRepository kdvVergisiRepository;
    private final KdvVergisiMapper kdvVergisiMapper;
    private final VergiHesaplayiciFactory vergiHesaplayiciFactory;

    @Override
    @Transactional
    public KdvVergisiResponse createKdvVergisi(Alisveris alisveris, Kullanici kullanici) {
        VergiHesaplayiciStrategy hesaplayici = vergiHesaplayiciFactory.getStrategy(VergiTuru.KDV);
        BigDecimal fiyat = hesaplayici.hesapla(alisveris.getTutar(), kullanici);

        KdvVergisi kdv = new KdvVergisi();
        kdv.setFiyat(fiyat);
        kdv.setUrunTuru(alisveris.getUrunTuru());
        kdv.setAlisveris(alisveris);

        KdvVergisi saved = kdvVergisiRepository.save(kdv);
        return kdvVergisiMapper.kdvVergisiToKdvVergisiResponse(saved);
    }

    @Override
    public List<KdvVergisiResponse> getAllByAlisverisId(Long alisverisId) {
        List<KdvVergisiResponse> list = new ArrayList<>();
        for (KdvVergisi kdvVergisi : kdvVergisiRepository.findByAlisverisId(alisverisId)) {
            KdvVergisiResponse kdvVergisiResponse = kdvVergisiMapper.kdvVergisiToKdvVergisiResponse(kdvVergisi);
            list.add(kdvVergisiResponse);
        }
        return list;
    }

//    @Override
//    public List<KdvVergisiResponse> getAllByAlisverisId(Long alisverisId) {
//        return kdvVergisiRepository.findByAlisverisId(alisverisId).stream()
//                .map(kdvVergisiMapper::kdvVergisiToKdvVergisiResponse)
//                .toList();
//    }

    @Override
    public KdvVergisiResponse getKdvVergisiById(Long id) {
        KdvVergisi vergi = kdvVergisiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("KDV Vergisi bulunamadı: " + id));
        return kdvVergisiMapper.kdvVergisiToKdvVergisiResponse(vergi);
    }
}
