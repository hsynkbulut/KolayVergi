package com.kolayvergi.service.vergi;

import com.kolayvergi.dto.response.vergi.OtvVergisiResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.vergi.OtvVergisi;

import java.util.List;
import java.util.UUID;

public interface OtvVergisiService {
    OtvVergisi createOtvVergisi(Alisveris alisveris, Kullanici kullanici);
    List<OtvVergisiResponse> getAllByAlisverisId(UUID alisverisId);
    OtvVergisiResponse getOtvVergisiById(UUID id);
    OtvVergisi updateOtvVergisi(Alisveris alisveris, Kullanici kullanici);
}
