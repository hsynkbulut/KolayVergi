package com.kolayvergi.controller.impl;

import com.kolayvergi.controller.KullaniciController;
import com.kolayvergi.dto.request.KullaniciCreateRequest;
import com.kolayvergi.dto.request.KullaniciUpdateRequest;
import com.kolayvergi.dto.response.KullaniciResponse;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.service.KullaniciService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class KullaniciControllerImpl implements KullaniciController {

    private final KullaniciService kullaniciService;

    @Override
    public ResponseEntity<KullaniciResponse> createKullanici(KullaniciCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(kullaniciService.createKullanici(request));
    }

    @Override
    public ResponseEntity<KullaniciResponse> getKullaniciById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(kullaniciService.getKullaniciById(id));
    }

    @Override
    public ResponseEntity<KullaniciResponse> updateKullanici(Long id, KullaniciUpdateRequest updateKullaniciRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(kullaniciService.updateKullanici(id, updateKullaniciRequest));
    }

    @Override
    public ResponseEntity<Void> deleteKullanici(Long id) {
        kullaniciService.deleteKullanici(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

