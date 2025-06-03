package com.kolayvergi.service.impl;

import com.kolayvergi.constant.AlisverisConstants;
import com.kolayvergi.dto.mapper.AlisverisMapper;
import com.kolayvergi.dto.request.AlisverisCreateRequest;
import com.kolayvergi.dto.response.AlisverisResponse;
import com.kolayvergi.entity.*;
import com.kolayvergi.entity.enums.OdemeDurumu;
import com.kolayvergi.entity.enums.UrunTuru;
import com.kolayvergi.repository.AlisverisRepository;
import com.kolayvergi.service.*;
import com.kolayvergi.service.vergi.VergiHesaplamaService;
import com.kolayvergi.service.vergi.VergiHesaplamaSonuc;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlisverisServiceImpl implements AlisverisService {

    private final AlisverisRepository alisverisRepository;
    private final KullaniciService kullaniciService;
    private final AlisverisMapper alisverisMapper;
    private final AracBilgisiService aracBilgisiService;
    private final OdemePlaniService odemePlaniService;
    private final VergiHesaplamaService vergiHesaplamaService;
    private final BorcService borcService;

    @Override
    @Transactional
    public AlisverisResponse createAlisveris(AlisverisCreateRequest request) {
        Kullanici kullanici = kullaniciService.getCurrentUser();
        Alisveris alisveris = alisverisMapper.aliverisCreateRequestToAlisveris(request);
        alisveris.setKullanici(kullanici);

        if (request.getUrunTuru() == UrunTuru.OTOMOBIL && request.getAracBilgisi() == null) {
            throw new IllegalArgumentException(AlisverisConstants.OTOMOBIL_ARAC_BILGISI_ZORUNLU);
        }
        alisveris = alisverisRepository.save(alisveris);

        VergiHesaplamaSonuc sonuc = vergiHesaplamaService.hesaplaVergiler(alisveris, kullanici);
        OdemePlani odemePlani = odemePlaniService.createOdemePlaniForAlisveris(alisveris, sonuc.getToplamVergiTutari());
        alisveris.setOdemePlani(odemePlani);

        return alisverisMapper.alisverisToAlisverisResponse(alisveris);
    }

    @Override
    public AlisverisResponse getAlisveris(UUID id) {
        Alisveris alisveris = alisverisRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(AlisverisConstants.ALISVERIS_BULUNAMADI, id)));
        return alisverisMapper.alisverisToAlisverisResponse(alisveris);
    }

    @Transactional()
    @Override
    public void deleteAlisveris(UUID id) {
        if (!alisverisRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format(AlisverisConstants.SILINECEK_ALISVERIS_BULUNAMADI, id));
        }
        Alisveris dbAlisveris = alisverisRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(AlisverisConstants.ALISVERIS_BULUNAMADI, id)));
        Kullanici kullanici = kullaniciService.getCurrentUser();
        BigDecimal odenecekTutar = dbAlisveris.getOdemePlani().getToplamOdenecekTutar();
        BigDecimal odenmisTutar = BigDecimal.ZERO;
        List<Taksit> taksitler = dbAlisveris.getOdemePlani().getTaksitler();
        BigDecimal taksitTutari = odenecekTutar.divide(
                BigDecimal.valueOf(dbAlisveris.getTaksitSayisi()),
                MathContext.DECIMAL128
        );
        for (Taksit taksit : taksitler) {
            if (taksit.getDurum() == OdemeDurumu.ODENDI) {
                odenmisTutar = odenmisTutar.add(taksitTutari);
            }
        }

        Borc dbBorc = kullanici.getBorc();
        BigDecimal kalanBorc = odenecekTutar.subtract(odenmisTutar);
        dbBorc.setToplamBorc(dbBorc.getToplamBorc().subtract(odenecekTutar));
        dbBorc.setKalanBorc(dbBorc.getKalanBorc().subtract(kalanBorc));
        alisverisRepository.deleteById(id);
    }

    @Override
    public List<AlisverisResponse> getCurrentUserAlisverisler() {
        Kullanici kullanici = kullaniciService.getCurrentUser();
        List<Alisveris> alisverisler = alisverisRepository.findAllByKullaniciId(kullanici.getId());
        return alisverisler.stream()
                .map(alisverisMapper::alisverisToAlisverisResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional()
    public AlisverisResponse updateAlisveris(UUID id, AlisverisCreateRequest request) {
        deleteAlisveris(id);
        return createAlisveris(request);
    }
}
