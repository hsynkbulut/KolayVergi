package com.kolayvergi.controller.impl;

import com.kolayvergi.controller.KullaniciController;
import com.kolayvergi.dto.response.KullaniciResponse;
import com.kolayvergi.service.KullaniciService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class KullaniciControllerImpl implements KullaniciController {

    private final KullaniciService kullaniciService;

    @Override
    public ResponseEntity<KullaniciResponse> getKullaniciById(UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(kullaniciService.getKullaniciById(id));
    }

    @Override
    public ResponseEntity<Void> deleteKullanici() {
        kullaniciService.deleteKullanici();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<KullaniciResponse>> getAllKullanicilar() {
        return ResponseEntity.ok(kullaniciService.getAllKullanicilar());
    }
}

