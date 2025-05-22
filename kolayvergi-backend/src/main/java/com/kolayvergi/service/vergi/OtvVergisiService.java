package com.kolayvergi.service.vergi;

import com.kolayvergi.dto.response.vergi.OtvVergisiResponse;
import com.kolayvergi.entity.Alisveris;
import com.kolayvergi.entity.Kullanici;
import com.kolayvergi.entity.vergi.OtvVergisi;

import java.util.List;

public interface OtvVergisiService {
    OtvVergisi createOtvVergisi(Alisveris alisveris, Kullanici kullanici);
    List<OtvVergisiResponse> getAllByAlisverisId(Long alisverisId);
    OtvVergisiResponse getOtvVergisiById(Long id);
}
