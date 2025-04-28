//package com.kolayvergi.service.impl;
//
//import com.kolayvergi.entity.OdemePlani;
//import com.kolayvergi.entity.Taksit;
//import com.kolayvergi.entity.enums.OdemeDurumu;
//import com.kolayvergi.entity.enums.OdemeTuru;
//import com.kolayvergi.repository.TaksitRepository;
//import com.kolayvergi.service.OdemePlaniService;
//import com.kolayvergi.service.TaksitService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class TaksitServiceImpl implements TaksitService {
//
//    private final TaksitRepository taksitRepository;
//    private final OdemePlaniService odemePlaniService;
//
//    @Override
//    @Transactional
//    public List<Taksit> createInitialTaksitler(OdemePlani odemePlani, List<OdemeTuru> odemeTurleri) {
//        int taksitSayisi = odemePlani.getToplamTaksitSayisi();
//        BigDecimal toplamTutar = odemePlani.getToplamOdenecekTutar();
//
//        if (odemeTurleri.size() != taksitSayisi) {
//            throw new IllegalArgumentException("Taksit sayısı ile ödeme türleri listesi uyumsuz!");
//        }
//
//        BigDecimal taksitTutari = toplamTutar
//                .divide(BigDecimal.valueOf(taksitSayisi), 2, RoundingMode.HALF_UP);
//
//        List<Taksit> taksitler = new ArrayList<>();
//
//        for (int i = 0; i < taksitSayisi; i++) {
//            Taksit taksit = new Taksit();
//            taksit.setOdemePlani(odemePlani);
//            taksit.setTaksitNo(generateTaksitNo(odemePlani, i + 1));
//            taksit.setTaksitTutari(taksitTutari);
//            taksit.setSonOdemeTarihi(LocalDate.now().plusMonths(i + 1));
//            taksit.setOdemeTarihi(null);
//            taksit.setDurum(OdemeDurumu.ODENMEDI);
//            taksit.setOdemeTuru(odemeTurleri.get(i)); // ❗️ Her taksit için bağımsız ödeme türü atanıyor
//
//            taksitler.add(taksit);
//        }
//
//        return taksitler;
//    }
//
//    private String generateTaksitNo(OdemePlani odemePlani, int index) {
//        String vergiTuru = odemePlani.getAlisveris().getUrunTuru().name();
//        LocalDate now = LocalDate.now();
//        String datePart = now.format(java.time.format.DateTimeFormatter.ofPattern("ddMMyyyy_HHmm"));
//        String kullaniciId = String.format("%03d", odemePlani.getAlisveris().getKullanici().getId());
//        String indexStr = String.format("%03d", index);
//        String randomCheck = String.format("%04d", (int) (Math.random() * 9999));
//        String numerator = String.format("%04d", index);
//
//        return vergiTuru + "_" + datePart + "_" + kullaniciId + "_" + indexStr + "_" + randomCheck + "_" + numerator;
//    }
//
//
//    @Override
//    @Transactional
//    public Taksit updateTaksitOdeme(Long taksitId) {
//        Taksit taksit = taksitRepository.findById(taksitId)
//                .orElseThrow(() -> new RuntimeException("Taksit bulunamadı!"));
//
//        if (taksit.getDurum() == OdemeDurumu.ODENDI) {
//            throw new IllegalStateException("Bu taksit zaten ödenmiş!");
//        }
//
//        taksit.setDurum(OdemeDurumu.ODENDI);
//        taksit.setOdemeTarihi(LocalDate.now());
//
//        Taksit updatedTaksit = taksitRepository.save(taksit);
//
//        // Taksit ödendi, OdemePlani'ni güncelle
//        odemePlaniService.updateOdemePlaniAfterPayment(updatedTaksit);
//
//        return updatedTaksit;
//    }
//}
