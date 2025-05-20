package com.kolayvergi.service.vergi;

import com.kolayvergi.dto.response.vergi.AracOtvVergisiResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.vergi.OtvVergisi;

import java.util.List;

public interface AracOtvVergisiService {
    OtvVergisi createAracOtvVergisi(Alisveris alisveris, Kullanici kullanici);
    List<AracOtvVergisiResponse> getAllByAlisverisId(Long alisverisId);
    AracOtvVergisiResponse getAracOtvVergisiById(Long id);
}
