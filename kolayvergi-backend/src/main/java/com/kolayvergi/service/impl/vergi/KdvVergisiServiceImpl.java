package com.kolayvergi.service.impl.vergi;


import com.kolayvergi.dto.mapper.KdvVergisiMapper;
import com.kolayvergi.dto.response.vergi.KdvVergisiResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.vergi.KdvVergisi;
import com.kolayvergi.hesaplayici.KdvVergisiHesaplayici;
import com.kolayvergi.repository.KdvVergisiRepository;
import com.kolayvergi.service.vergi.KdvVergisiService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KdvVergisiServiceImpl implements KdvVergisiService {

    private final KdvVergisiRepository kdvVergisiRepository;
    private final KdvVergisiMapper kdvVergisiMapper;
    private final KdvVergisiHesaplayici kdvVergisiHesaplayici;

    @Override
    @Transactional
    public KdvVergisi createKdvVergisi(Alisveris alisveris, Kullanici kullanici) {
        KdvVergisi kdvVergisi = kdvVergisiHesaplayici.hesapla(alisveris, kullanici);
        return kdvVergisiRepository.save(kdvVergisi);
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
