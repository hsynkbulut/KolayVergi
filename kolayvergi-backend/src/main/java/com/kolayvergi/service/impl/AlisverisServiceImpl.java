package com.kolayvergi.service.impl;

import com.kolayvergi.dto.mapper.AlisverisMapper;
import com.kolayvergi.dto.request.AlisverisCreateRequest;
import com.kolayvergi.dto.request.AlisverisUpdateRequest;
import com.kolayvergi.dto.response.AlisverisResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.repository.AlisverisRepository;
import com.kolayvergi.service.AlisverisService;
import com.kolayvergi.service.KullaniciService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlisverisServiceImpl implements AlisverisService {

    private final AlisverisRepository alisverisRepository;
    private final AlisverisMapper alisverisMapper;
    private final KullaniciService kullaniciService;

    @Transactional(readOnly = false)
    @Override
    public AlisverisResponse createAlisveris(AlisverisCreateRequest request) {
        //urun turu tutar sececek
        //kdv vergi hesaplayıcı methodu burada calısacak.
        //kdv tablosuna kayıt atacak
        //hesaplanmıs degısken ı odeme planına tutara ekleyıp kayıt atacak


        Kullanici kullanici = kullaniciService.getKullanici(request.getKullaniciId());
        Alisveris alisveris = alisverisMapper.aliverisCreateRequestToAlisveris(request);
        alisveris.setKullanici(kullanici);
        Alisveris dbAlisveris = alisverisRepository.save(alisveris);
        return alisverisMapper.alisverisToAlisverisResponse(dbAlisveris);
    }

    @Override
    public AlisverisResponse getAlisveris(Long id){
        Alisveris alisveris = getAlisverisById(id);
        return alisverisMapper.alisverisToAlisverisResponse(alisveris);
    }

    @Transactional(readOnly = false)
    @Override
    public AlisverisResponse updateAlisveris(Long id, AlisverisUpdateRequest updateAlisveris) {
        Alisveris alisveris = getAlisverisById(id);
        Optional.ofNullable(updateAlisveris.getUrunTuru()).ifPresent(alisveris::setUrunTuru); //method reference
        Optional.ofNullable(updateAlisveris.getTutar()).ifPresent(tutar -> alisveris.setTutar(tutar)); //lambda
        Alisveris updatedAlisveris = alisverisRepository.save(alisveris);
        return alisverisMapper.alisverisToAlisverisResponse(updatedAlisveris);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteAlisveris(Long id) {
        alisverisRepository.deleteById(id);
    }

    public Alisveris getAlisverisById(Long id){
        return alisverisRepository.findById(id).orElseThrow(RuntimeException::new);
    }

}
