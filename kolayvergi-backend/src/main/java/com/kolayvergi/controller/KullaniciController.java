package com.kolayvergi.controller;

import com.kolayvergi.constant.ApiConstants;
import com.kolayvergi.dto.request.KullaniciCreateRequest;
import com.kolayvergi.dto.request.KullaniciUpdateRequest;
import com.kolayvergi.dto.response.KullaniciResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiConstants.KULLANICILAR)
public interface KullaniciController {

    @PostMapping
    ResponseEntity<KullaniciResponse> createKullanici(@RequestBody @Valid KullaniciCreateRequest request);

    @GetMapping("/{id}")
    ResponseEntity<KullaniciResponse> getKullaniciById(@PathVariable(name = "id") Long id);

    @PutMapping("/{id}")
    ResponseEntity<KullaniciResponse> updateKullanici(@PathVariable(name = "id") Long id,@RequestBody @Valid KullaniciUpdateRequest updateKullaniciRequest);

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteKullanici(@PathVariable(name = "id") Long id);

}