package com.kolayvergi.service.vergi;

import com.kolayvergi.dto.response.vergi.AracOtvVergisiResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.Kullanici;

import java.util.List;

public interface AracOtvVergisiService {
    AracOtvVergisiResponse createAracOtvVergisi(Alisveris alisveris, Kullanici kullanici);
    List<AracOtvVergisiResponse> getAllByAlisverisId(Long alisverisId);
    AracOtvVergisiResponse getAracOtvVergisiById(Long id);
}
